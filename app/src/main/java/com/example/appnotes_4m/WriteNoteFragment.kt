package com.example.appnotes_4m

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnotes_4m.extension.setBackStackData
import com.example.repeatnavigation.databinding.FragmentWriteNoteBinding


class WriteNoteFragment : Fragment() {
    private lateinit var binding: FragmentWriteNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWriteNoteBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.apply {
            btnSave.setOnClickListener{
                val title: String = binding.etTitle.text.toString()
                setBackStackData(
                    "key", title,true)
                val pref = PreferenceHelper()
                pref.unit(requireContext())
                pref.text = title
            }
        }
    }
}