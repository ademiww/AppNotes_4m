package com.example.appnotes_4m.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnotes_4m.App
import com.example.appnotes_4m.PreferenceHelper
import com.example.appnotes_4m.adapters.Adapter
import com.example.appnotes_4m.data.model.Note
import com.example.appnotes_4m.intetface.OnClickItem
import com.example.repeatnavigation.R
import com.example.repeatnavigation.databinding.FragmentNoteListBinding


class ListNoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteListBinding
    private lateinit var noteAdapter: Adapter
    private val sharedPreferences = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences.init(requireContext())

        val isLinearLayout = sharedPreferences.isLinearLayout()
        setRecyclerViewLayout(isLinearLayout)
        noteAdapter = Adapter(this, this)
        initialize()
        setupListener()
        getData()
    }

    private fun initialize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setRecyclerViewLayout(isLinearLayout: Boolean) {
        binding.recyclerView.layoutManager = if (isLinearLayout) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setupListener() = with(binding) {
        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }
        btnChange.setOnClickListener {

            val isCurrentlyLinear = binding.recyclerView.layoutManager is LinearLayoutManager
            setRecyclerViewLayout(!isCurrentlyLinear)

            sharedPreferences.setLinearLayout(!isCurrentlyLinear)
        }
    }

    private fun getData() {
        App.appDataBase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { listNote ->
            noteAdapter.submitList(listNote)
        }
    }

    override fun onLongClick(noteModel: Note) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Удалить заметку?")
            setPositiveButton("Удалить") { _, _ ->
                App.appDataBase?.noteDao()?.deleteNote(noteModel)
                getData()
            }
            setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: Note) {
        val action = ListNoteFragmentDirections.actionNoteListFragmentToAddNoteFragment(noteModel.id)
        findNavController().navigate(action)
    }
}