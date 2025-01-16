package com.example.appnotes_4m.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnotes_4m.PreferenceHelper
import com.example.appnotes_4m.adapters.NotesAdapter
import com.example.appnotes_4m.extension.getBackStackData
import com.example.repeatnavigation.R
import com.example.repeatnavigation.databinding.FragmentNotesBinding
import com.example.appnotes_4m.models.Notes

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NotesAdapter
    private val notes: ArrayList<Notes> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initialize()
    }

    private fun initialize() {
        adapter = NotesAdapter(notes)
        binding.rvNotes.adapter = adapter

        binding.btnAdd.setOnClickListener() {
            findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToWriteNoteFragment())
        }
        val pref = PreferenceHelper()
        pref.unit(requireContext())
        // pref.text?.let { Notes(it) }?.let { notes.add(it) }
    }

    private fun getData() {
        getBackStackData<String>("key") {
            val note = Notes(it)
            notes.add(note)
            adapter.submitList(notes)

        }
    }
}
