package com.example.social_guard_dev.ui.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.social_guard_dev.MainActivity
import com.example.social_guard_dev.R
import com.example.social_guard_dev.ui.receivers.NotificationReceiver

object NotificationHelper {
    private const val CHANNEL_ID = "app_usage_alerts"
    private const val CHANNEL_NAME = "App Usage Alerts"
    private const val CHANNEL_DESC = "Notifications about app usage limits"
    private const val GROUP_KEY = "com.example.social_guard_dev.APP_USAGE_GROUP"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                importance
            ).apply {
                description = CHANNEL_DESC
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 100, 200, 100)
                setShowBadge(true)
            }

            val notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showTimeLimitNotification(
        context: Context,
        appName: String,
        packageName: String,
        usageMillis: Long,
        limitMillis: Long
    ) {
        // Create intent to open app when notification tapped
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("targetFragment", "home")
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Format usage information
        val usageMinutes = usageMillis / 60000
        val limitMinutes = limitMillis / 60000
        val overMinutes = (usageMinutes - limitMinutes).coerceAtLeast(0)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Time Limit Reached - $appName")
            .setContentText("Used $usageMinutes mins (Limit: $limitMinutes mins)")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("You've used $appName for $overMinutes minutes over your limit. Consider taking a break."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setGroup(GROUP_KEY)
            .addAction(
                R.drawable.ic_snooze,
                "Snooze 15 min",
                createSnoozePendingIntent(context, packageName)
            )
            .addAction(
                R.drawable.ic_settings,
                "Adjust Limit",
                createSettingsPendingIntent(context)
            )

        with(NotificationManagerCompat.from(context)) {
            notify(packageName.hashCode(), builder.build())
        }
    }

    private fun createSnoozePendingIntent(context: Context, packageName: String): PendingIntent {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = "SNOOZE"
            putExtra("packageName", packageName)
        }
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun createSettingsPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra("targetFragment", "home")
            putExtra("openTimerDialog", true)
        }
        return PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    fun showSummaryNotification(context: Context, exceededApps: List<String>) {
        if (exceededApps.isEmpty()) return

        val inboxStyle = NotificationCompat.InboxStyle()
            .setSummaryText("${exceededApps.size} apps over limit")

        exceededApps.take(5).forEach { appName ->
            inboxStyle.addLine("• $appName")
        }

        if (exceededApps.size > 5) {
            inboxStyle.addLine("• and ${exceededApps.size - 5} more")
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Daily Usage Summary")
            .setContentText("${exceededApps.size} apps exceeded limits")
            .setStyle(inboxStyle)
            .setGroup(GROUP_KEY)
            .setGroupSummary(true)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify("summary".hashCode(), builder.build())
        }
    }
}