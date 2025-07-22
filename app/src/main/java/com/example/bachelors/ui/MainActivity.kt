package com.example.bachelors.ui
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.bachelors.R
import com.example.bachelors.features.common.MainViewmodel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewmodel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()/*.setKeepOnScreenCondition{
            !viewModel.isFetchDone.value
        }
*/
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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
