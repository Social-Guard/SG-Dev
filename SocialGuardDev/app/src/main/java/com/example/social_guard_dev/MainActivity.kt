package com.example.social_guard_dev

import android.app.AppOpsManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.social_guard_dev.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Step 1: Check and request usage stats permission
        checkAndRequestUsageStatsPermission()

        // Step 2: Retrieve app usage data
        retrieveAppUsageData()

        // Example: Set a time limit for an app (e.g., 30 minutes for "com.example.someapp")
        exampleSetTimeLimit()
    }

    // Step 1: Check and request usage stats permission
    private fun checkAndRequestUsageStatsPermission() {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )

        if (mode != AppOpsManager.MODE_ALLOWED) {
            // Permission not granted, redirect to settings
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }
    }

    // Step 2: Retrieve app usage data and check against time limits
    private fun retrieveAppUsageData() {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val startTime = calendar.timeInMillis

        val usageStatsList = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )

        for (usageStats in usageStatsList) {
            Log.d(
                "AppUsage",
                "Package: ${usageStats.packageName}, Usage Time: ${usageStats.totalTimeInForeground} ms"
            )

            // Check the app's usage time against the timer limit
            checkAppUsageAgainstTimer(usageStats)
        }
    }

    // Check if the app usage time exceeds the set timer limit
    private fun checkAppUsageAgainstTimer(usageStats: UsageStats) {
        val appLimit = getAppTimeLimit(usageStats.packageName) // Get the time limit from SharedPreferences

        // Convert the usage time from milliseconds to minutes
        val usageTimeInMinutes = usageStats.totalTimeInForeground / (1000 * 60)

        if (usageTimeInMinutes > appLimit) {
            // If usage time exceeds the limit, take action (e.g., notify the user or block the app)
            Log.d("AppUsage", "${usageStats.packageName} has exceeded the usage limit!")

            // Show time limit exceeded dialog
            showTimeLimitExceededDialog(usageStats.packageName)
        }
    }

    // Show a dialog or notification when the app's usage limit is exceeded
    private fun showTimeLimitExceededDialog(packageName: String) {
        // Example: Show a Toast message, you can replace this with a dialog or notification
        Toast.makeText(this, "Time limit exceeded for app: $packageName", Toast.LENGTH_LONG).show()

        // Optionally, you could show a more elaborate dialog or take other actions here
    }

    // Save the app's time limit in SharedPreferences
    private fun setAppTimeLimit(packageName: String, limitInMinutes: Long) {
        val sharedPreferences = getSharedPreferences("AppTimers", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putLong(packageName, limitInMinutes)
            apply()
        }
    }

    // Retrieve the app's time limit from SharedPreferences
    private fun getAppTimeLimit(packageName: String): Long {
        val sharedPreferences = getSharedPreferences("AppTimers", Context.MODE_PRIVATE)
        return sharedPreferences.getLong(packageName, 0) // Default limit is 0 (no limit)
    }

    // Example of how to set a time limit for a specific app (e.g., 30 minutes)
    private fun exampleSetTimeLimit() {
        val packageName = "com.example.someapp" // Replace with actual app package name
        val limitInMinutes = 30L // Set limit to 30 minutes
        setAppTimeLimit(packageName, limitInMinutes)
    }
}

