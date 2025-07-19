package com.example.bachelors.ui

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val NOTIFICATION_PERMISSION_CODE = 101


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
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
        checkNotificationPermission()
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // optionally, show rationale here
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("MainActivity", "Notification permission granted")
            } else {
                Log.d("MainActivity", "Notification permission denied")
            }
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
