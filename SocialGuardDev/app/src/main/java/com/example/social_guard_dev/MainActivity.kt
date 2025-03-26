package com.example.social_guard_dev

import android.app.AppOpsManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.social_guard_dev.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Notification channel ID and name
    private val CHANNEL_ID = "AppTimerChannel"
    private val CHANNEL_NAME = "App Timer Notifications"

    // RecyclerView and Adapter for app usage data
    private lateinit var appUsageAdapter: AppUsageAdapter
    private lateinit var recyclerView: RecyclerView

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

        // Initialize RecyclerView
        recyclerView = binding.appUsageRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        appUsageAdapter = AppUsageAdapter()
        recyclerView.adapter = appUsageAdapter

        // Step 1: Check and request usage stats permission
        checkAndRequestUsageStatsPermission()

        // Step 2: Retrieve and display app usage data
        retrieveAndDisplayAppUsageData()

        // Step 3: Create notification channel
        createNotificationChannel()

        // Step 4: Request notification permission (for Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }
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

    // Step 2: Retrieve and display app usage data
    private fun retrieveAndDisplayAppUsageData() {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1) // Retrieve data for the last 24 hours
        val startTime = calendar.timeInMillis

        val usageStatsList = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        )

        // Filter out apps with 0 usage time and sort by usage time
        val filteredStats = usageStatsList.filter { it.totalTimeInForeground > 0 }
            .sortedByDescending { it.totalTimeInForeground }

        // Update the RecyclerView with the app usage data
        appUsageAdapter.updateData(filteredStats)

        // Check app usage against timers
        for (usageStats in filteredStats) {
            checkAppUsageAgainstTimer(usageStats)
        }
    }

    // Step 3: Create a notification channel (required for Android 8.0+)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for app timer limits"
            }

            // Register the channel with the system
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Step 4: Show a notification
    private fun showNotification(title: String, message: String) {
        val notificationId = System.currentTimeMillis().toInt() // Unique ID for the notification

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification) // Set your notification icon
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    // Check if the app usage time exceeds the set timer limit
    private fun checkAppUsageAgainstTimer(usageStats: UsageStats) {
        val appLimit = getAppTimeLimit(usageStats.packageName) // Get the time limit from SharedPreferences

        // Convert the usage time from milliseconds to milliseconds (for consistency)
        val usageTimeInMillis = usageStats.totalTimeInForeground

        Log.d("AppUsage", "Checking ${usageStats.packageName}: Usage = $usageTimeInMillis ms, Limit = $appLimit ms")

        if (usageTimeInMillis > appLimit && appLimit > 0) {
            // If usage time exceeds the limit, notify the user
            Log.d("AppUsage", "${usageStats.packageName} has exceeded the usage limit!")
            showNotification(
                "App Timer Limit Reached",
                "You have exceeded your usage limit for ${getAppName(usageStats.packageName)}."
            )
        }
    }

    // Save the app's time limit in SharedPreferences
    private fun setAppTimeLimit(packageName: String, limitInMillis: Long) {
        val sharedPreferences = getSharedPreferences("AppTimers", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putLong(packageName, limitInMillis)
            apply()
        }
    }

    // Retrieve the app's time limit from SharedPreferences
    private fun getAppTimeLimit(packageName: String): Long {
        val sharedPreferences = getSharedPreferences("AppTimers", Context.MODE_PRIVATE)
        return sharedPreferences.getLong(packageName, 0) // Default limit is 0 (no limit)
    }

    // Get the app name from the package name
    private fun getAppName(packageName: String): String {
        return try {
            val packageManager = packageManager
            packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0)).toString()
        } catch (e: Exception) {
            packageName // Fallback to package name if app name cannot be retrieved
        }
    }

    // Show a dialog to set a time limit for the app
    private fun showSetTimerDialog(packageName: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_set_timer, null)
        val editText = dialogView.findViewById<EditText>(R.id.timerEditText)
        val spinner = dialogView.findViewById<Spinner>(R.id.timeUnitSpinner)

        // Set up the spinner with time units
        val timeUnits = arrayOf("Seconds", "Minutes", "Hours")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val dialog = AlertDialog.Builder(this)
            .setTitle("Set Timer for ${getAppName(packageName)}")
            .setView(dialogView)
            .setPositiveButton("Set") { _, _ ->
                val timeLimit = editText.text.toString().toLongOrNull()
                if (timeLimit != null && timeLimit > 0) {
                    val selectedUnit = spinner.selectedItem as String
                    val limitInMillis = when (selectedUnit) {
                        "Seconds" -> timeLimit * 1000
                        "Minutes" -> timeLimit * 1000 * 60
                        "Hours" -> timeLimit * 1000 * 60 * 60
                        else -> 0
                    }
                    if (limitInMillis > 0) {
                        setAppTimeLimit(packageName, limitInMillis)
                        Toast.makeText(this, "Timer set for ${getAppName(packageName)}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Invalid time limit", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Invalid time limit", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    // Adapter for displaying app usage data in a RecyclerView
    inner class AppUsageAdapter : RecyclerView.Adapter<AppUsageAdapter.AppUsageViewHolder>() {
        private val appUsageList = mutableListOf<UsageStats>()

        // Update the data in the adapter
        fun updateData(newData: List<UsageStats>) {
            appUsageList.clear()
            appUsageList.addAll(newData)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppUsageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_app_usage, parent, false)
            return AppUsageViewHolder(view)
        }

        override fun onBindViewHolder(holder: AppUsageViewHolder, position: Int) {
            val usageStats = appUsageList[position]
            holder.bind(usageStats)
        }

        override fun getItemCount(): Int = appUsageList.size

        inner class AppUsageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val appNameTextView: TextView = itemView.findViewById(R.id.appNameTextView)
            private val usageTimeTextView: TextView = itemView.findViewById(R.id.usageTimeTextView)
            private val setTimerButton: Button = itemView.findViewById(R.id.setTimerButton)

            fun bind(usageStats: UsageStats) {
                // Get the app name from the package name
                val appName = getAppName(usageStats.packageName)

                // Convert usage time to a readable format (e.g., minutes and seconds)
                val usageTime = usageStats.totalTimeInForeground / (1000 * 60) // Convert to minutes

                // Bind data to the views
                appNameTextView.text = appName
                usageTimeTextView.text = "Usage: $usageTime minutes"

                // Set a click listener for the "Set Timer" button
                setTimerButton.setOnClickListener {
                    showSetTimerDialog(usageStats.packageName)
                }
            }
        }
    }
}