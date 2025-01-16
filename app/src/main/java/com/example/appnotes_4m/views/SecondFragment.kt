package com.example.appnotes_4m.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repeatnavigation.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            val name = bundle.getString("name", "no name")
            val email = bundle.getString("email", "no email")
            val password = bundle.getString("password", "no password")

            binding.tvGetName.text = "Name: $name"
            binding.tvGetEmail.text = "Email: $email"
            binding.tvGetPassword.text = "Password: $password"
        }

    }
}