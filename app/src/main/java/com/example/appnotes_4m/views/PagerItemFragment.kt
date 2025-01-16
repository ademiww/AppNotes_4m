package com.example.appnotes_4m.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.appnotes_4m.PreferenceHelper
import com.example.appnotes_4m.adapters.OnBoardAdapter
import com.example.repeatnavigation.R
import com.example.repeatnavigation.databinding.FragmentOnBoardBinding
import com.example.repeatnavigation.databinding.FragmentPagerItemBinding


class PagerItemFragment : Fragment() {
    private lateinit var binding : FragmentPagerItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPagerItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        initialize()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardAdapter(this@PagerItemFragment)
    }

    private fun setUpListener() = with(binding.viewPager){
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 1){
                    binding.viewPagerBtn.text = "Sign In"
                    binding.viewPagerBtn.setOnClickListener{
                        val pref = PreferenceHelper()
                        pref.unit(requireContext())
                        pref.isOnBoardShown = true
                        findNavController().navigate(PagerItemFragmentDirections.actionPagerItemFragmentToFirstFragment())
                    }
                } else {
                    binding.viewPagerBtn.text = "Text"
                }
            }
        })
        binding.viewPagerBtn.setOnClickListener{
            if (currentItem < 3){
                setCurrentItem(currentItem + 2, true)
                requireActivity()
            }
        }
    }
}