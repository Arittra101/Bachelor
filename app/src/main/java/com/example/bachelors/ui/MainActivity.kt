package com.example.bachelors.ui


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.bachelors.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_bar)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigationView) { view, insets ->


            view.setPadding(0, 0, 0, 0) // remove padding if any
            insets // return the original insets if you want default behavior
        }

    }
}


/*
🧠 Core Difference
Aspect	fragment.findNavController()	activity.findFragmentById(...).navController
📍 Where it's used	Inside a fragment	Inside an activity
🔍 How it finds NavController	Automatically walks up to find the nearest NavHostFragment	Manually finds the NavHostFragment using ID
✅ When to use	When you already have a Fragment and want to navigate from it	When you're in the Activity and need global access to NavController
🧩 Simpler for	Reusable utility functions inside fragments	App-level setup like setupWithNavController()




 This connects the BottomNavigationView with the NavController
NavigationUI.setupWithNavController(bottomNavigationView, navController)
  val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
   val navController: NavController = navHostFragment.navController
      val navInflater: NavInflater = navController.navInflater
       val navGraph: NavGraph = navInflater.inflate(R.navigation.nav_graph)
     navGraph.setStartDestination(R.id.blankFragment)


        navController.graph = navGraph
*/
