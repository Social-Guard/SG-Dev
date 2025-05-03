package com.example.social_guard_dev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.social_guard_dev.databinding.ActivityMainBinding
import com.example.social_guard_dev.ui.utils.NotificationHelper
import com.example.social_guard_dev.viewmodels.UsageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize notification channel
        NotificationHelper.createNotificationChannel(this)

        // Start periodic usage checks
        val usageViewModel = ViewModelProvider(this)[UsageViewModel::class.java]
        usageViewModel.startPeriodicUsageCheck()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_trends, R.id.navigation_tasks)
        ).also { appBarConfiguration ->
            setupActionBarWithNavController(navController, appBarConfiguration)
        }

        navView.setupWithNavController(navController)
    }
}
