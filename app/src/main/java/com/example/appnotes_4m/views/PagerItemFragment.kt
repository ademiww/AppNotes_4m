package com.example.appnotes_4m.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
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
        setupViewPager()
        setupDotsIndicator()
    }

    private fun setupViewPager() {
        val adapter = OnBoardAdapter(this)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position)
            }
        })
    }

    private fun setupDotsIndicator() {
        val pageCount = 2

        val dots = Array(pageCount) { ImageView(requireContext()) }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in dots.indices) {
            dots[i] = ImageView(requireContext()).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.dot_inactive
                    )
                )
                this.layoutParams = layoutParams
            }
            binding.dotsContainer.addView(dots[i])
        }

        dots[0].setImageDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.dot_active)
        )
    }

    private fun updateDots(currentPosition: Int) {
        val dots = binding.dotsContainer.children.toList()
        for (i in dots.indices) {
            val drawableRes = if (i == currentPosition) {
                R.drawable.dot_active
            } else {
                R.drawable.dot_inactive
            }
            (dots[i] as ImageView).setImageDrawable(
                ContextCompat.getDrawable(requireContext(), drawableRes)
            )
        }
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
                        findNavController().navigate(
                            PagerItemFragmentDirections.actionPagerItemFragmentToFirstFragment())
                    }
                } else {
                    binding.viewPagerBtn.text = "Next"
                }
            }
        })
        binding.viewPagerBtn.setOnClickListener{
            val currentItem = binding.viewPager.currentItem
            if (currentItem < 1) {
                binding.viewPager.setCurrentItem(currentItem + 1, true)
                requireActivity()
            }
        }
    }
}