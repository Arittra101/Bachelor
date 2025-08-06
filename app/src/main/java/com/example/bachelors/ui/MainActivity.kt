package com.example.bachelors.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.bachelors.R
import com.example.bachelors.core.common.navigateRoot
import com.example.bachelors.databinding.ActivityMainBinding
import com.example.bachelors.features.common.MainViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewmodel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigationBar
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        reduceBottomBarHeight(binding.bottomNavigationBar)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.historyDetailsFragment -> isVisibleBottomBar(false)
                else -> isVisibleBottomBar(true)
            }
        }

        binding.bottomNavigationBar.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.historyFragment -> {
                    navController.navigateRoot(R.id.historyFragment)
                    true
                }

                R.id.homeFragment -> {
                    navController.navigateRoot(R.id.homeFragment)
                    true
                }

                else -> false
            }
        }

    }

    private fun isVisibleBottomBar(isVisible: Boolean) {
        binding.bottomNavigationBar.isVisible = isVisible
    }

    private fun reduceBottomBarHeight(bottomNavigationView: BottomNavigationView) {
        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigationView) { view, insets ->
            view.setPadding(0, 0, 0, 0) // remove padding if any
            insets // return the original insets if you want default behavior
        }
    }
}