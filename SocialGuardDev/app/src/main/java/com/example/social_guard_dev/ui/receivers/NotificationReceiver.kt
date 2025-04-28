package com.example.social_guard_dev.ui.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.edit

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "SNOOZE" -> {
                val packageName = intent.getStringExtra("packageName") ?: return
                // Store snooze time (current time + 15 minutes)
                context.getSharedPreferences("AppTimers", Context.MODE_PRIVATE).edit {
                    putLong("${packageName}_snooze",
                        System.currentTimeMillis() + 15 * 60 * 1000)
                }

                // Cancel the notification
                NotificationManagerCompat.from(context).cancel(packageName.hashCode())
            }
        }
    }
}