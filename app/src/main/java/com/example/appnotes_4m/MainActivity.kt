package com.example.appnotes_4m

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.repeatnavigation.R
import com.example.repeatnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navContorller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment

        navContorller = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navContorller)
        val pref = PreferenceHelper()
        pref.unit(this)
        if (!pref.isOnBoardShown) {
            navContorller.navigate(R.id.pagerItemFragment)
        }

        navContorller.addOnDestinationChangedListener() {_, destination, _ ->
            if (destination.id == R.id.pagerItemFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}
