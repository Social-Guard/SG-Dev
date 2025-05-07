package com.example.social_guard_dev.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.example.social_guard_dev.R

object BatteryOptimizationHelper {
    fun checkBatteryOptimization(context: Context, onComplete: (Boolean) -> Unit = { _ -> }) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val packageName = context.packageName
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as android.os.PowerManager
            if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
                showBatteryOptimizationDialog(context, onComplete)
            } else {
                onComplete(true)
            }
        } else {
            onComplete(true)
        }
    }

    private fun showBatteryOptimizationDialog(context: Context, onComplete: (Boolean) -> Unit) {
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.battery_optimization_title))
            .setMessage(context.getString(R.string.battery_optimization_message))
            .setPositiveButton(context.getString(R.string.disable)) { _, _ ->
                try {
                    val intent = Intent().apply {
                        action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                        data = Uri.parse("package:${context.packageName}")
                    }
                    context.startActivity(intent)
                    onComplete(false)
                } catch (e: Exception) {
                    onComplete(false)
                }
            }
            .setNegativeButton(context.getString(R.string.later)) { _, _ ->
                onComplete(false)
            }
            .setOnDismissListener {
                onComplete(false)
            }
            .show()
    }
}