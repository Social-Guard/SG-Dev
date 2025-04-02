package com.example.social_guard_dev.data

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import java.util.*

class UsageStatsRepository(private val context: Context) {
    private val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    fun getTodayUsage(): List<UsageStats> {
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val startTime = calendar.timeInMillis

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            startTime,
            endTime
        ).filter { it.totalTimeInForeground > 0 }
    }

    fun getWeeklyUsage(): Map<String, Long> {
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.WEEK_OF_YEAR, -1)
        val startTime = calendar.timeInMillis

        return usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_WEEKLY,
            startTime,
            endTime
        ).filter { it.totalTimeInForeground > 0 }
            .groupBy { it.packageName }
            .mapValues { entry -> entry.value.sumOf { it.totalTimeInForeground } }
    }

    fun getDailyBreakdownForWeek(): Map<String, List<Long>> {
        val dailyUsage = mutableMapOf<String, MutableList<Long>>()
        val calendar = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -6) }

        repeat(7) { day ->
            val dayStart = calendar.timeInMillis
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val dayEnd = calendar.timeInMillis

            usageStatsManager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                dayStart,
                dayEnd
            ).forEach { stat ->
                if (stat.totalTimeInForeground > 0) {
                    val appName = getAppName(stat.packageName)
                    if (!dailyUsage.containsKey(appName)) {
                        dailyUsage[appName] = MutableList(7) { 0L }
                    }
                    dailyUsage[appName]?.set(day, stat.totalTimeInForeground)
                }
            }
        }
        return dailyUsage
    }

    fun getAppName(packageName: String): String {
        return try {
            context.packageManager.getApplicationLabel(
                context.packageManager.getApplicationInfo(packageName, 0)
            ).toString()
        } catch (e: Exception) {
            packageName
        }
    }
}

