package com.example.social_guard_dev.ui.home

import android.app.AppOpsManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.usage.UsageStats
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.social_guard_dev.R
import com.example.social_guard_dev.databinding.FragmentHomeBinding
import com.example.social_guard_dev.databinding.ItemAppUsageBinding
import com.example.social_guard_dev.viewmodels.UsageViewModel
import com.example.social_guard_dev.viewmodels.UsageViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val CHANNEL_ID = "AppTimerChannel"
    private val CHANNEL_NAME = "App Timer Notifications"
    private lateinit var appUsageAdapter: AppUsageAdapter

    private val viewModel: UsageViewModel by viewModels(
        factoryProducer = { UsageViewModelFactory(requireActivity().application) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        checkAndRequestUsageStatsPermission()
        createNotificationChannel()
        setupObservers()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (requireContext().checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (hasUsageStatsPermission()) {
            viewModel.refreshData()
        }
    }

    private fun setupRecyclerView() {
        binding.appUsageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        appUsageAdapter = AppUsageAdapter(viewModel) { packageName ->
            showSetTimerDialog(packageName)
        }
        binding.appUsageRecyclerView.adapter = appUsageAdapter
    }

    private fun setupObservers() {
        viewModel.todayUsage.observe(viewLifecycleOwner) { usageStats ->
            if (usageStats.isNotEmpty()) {
                appUsageAdapter.updateData(usageStats)
                binding.appUsageRecyclerView.visibility = View.VISIBLE
                binding.emptyStateView.visibility = View.GONE
                usageStats.forEach { checkAppUsageAgainstTimer(it) }
            } else {
                binding.appUsageRecyclerView.visibility = View.GONE
                binding.emptyStateView.visibility = View.VISIBLE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                binding.emptyStateView.visibility = View.VISIBLE
                binding.appUsageRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun checkAndRequestUsageStatsPermission() {
        if (!hasUsageStatsPermission()) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }
    }

    private fun hasUsageStatsPermission(): Boolean {
        val appOps = requireContext().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            requireContext().packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for app timer limits"
            }

            val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun checkAppUsageAgainstTimer(usageStats: UsageStats) {
        val appLimit = getAppTimeLimit(usageStats.packageName)
        if (usageStats.totalTimeInForeground > appLimit && appLimit > 0) {
            showNotification(
                "App Timer Limit Reached",
                "You have exceeded your usage limit for ${viewModel.getAppName(usageStats.packageName)}."
            )
        }
    }

    private fun getAppTimeLimit(packageName: String): Long {
        return requireContext().getSharedPreferences("AppTimers", Context.MODE_PRIVATE)
            .getLong(packageName, 0)
    }

    private fun setAppTimeLimit(packageName: String, limitInMillis: Long) {
        requireContext().getSharedPreferences("AppTimers", Context.MODE_PRIVATE).edit()
            .putLong(packageName, limitInMillis)
            .apply()
    }

    private fun showSetTimerDialog(packageName: String) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_set_timer, null)
        val editText = dialogView.findViewById<EditText>(R.id.timerEditText)
        val spinner = dialogView.findViewById<Spinner>(R.id.timeUnitSpinner)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.time_units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Set Timer for ${viewModel.getAppName(packageName)}")
            .setView(dialogView)
            .setPositiveButton("Set") { _, _ ->
                editText.text.toString().toLongOrNull()?.let { timeLimit ->
                    val limitInMillis = when (spinner.selectedItem as String) {
                        "Seconds" -> timeLimit * 1000
                        "Minutes" -> timeLimit * 60000
                        "Hours" -> timeLimit * 3600000
                        else -> 0
                    }
                    if (limitInMillis > 0) {
                        setAppTimeLimit(packageName, limitInMillis)
                        Toast.makeText(context, "Timer set", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }

    inner class AppUsageAdapter(
        private val viewModel: UsageViewModel,
        private val onSetTimerClick: (String) -> Unit
    ) : RecyclerView.Adapter<AppUsageAdapter.AppUsageViewHolder>() {

        private val appUsageList = mutableListOf<UsageStats>()

        fun updateData(newData: List<UsageStats>) {
            appUsageList.clear()
            appUsageList.addAll(newData.sortedByDescending { it.totalTimeInForeground })
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppUsageViewHolder {
            val binding = ItemAppUsageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AppUsageViewHolder(binding)
        }

        override fun onBindViewHolder(holder: AppUsageViewHolder, position: Int) {
            holder.bind(appUsageList[position])
        }

        override fun getItemCount() = appUsageList.size

        inner class AppUsageViewHolder(
            private val binding: ItemAppUsageBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(usageStats: UsageStats) {
                binding.appNameTextView.text = viewModel.getAppName(usageStats.packageName)
                binding.usageTimeTextView.text = formatTime(usageStats.totalTimeInForeground)
                binding.setTimerButton.setOnClickListener {
                    onSetTimerClick(usageStats.packageName)
                }
            }

            private fun formatTime(millis: Long): String {
                val minutes = millis / 60000
                return if (minutes >= 60) {
                    "${minutes / 60}h ${minutes % 60}m"
                } else {
                    "${minutes}m"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}