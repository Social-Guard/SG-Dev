package com.example.social_guard_dev

import android.app.Application
import com.example.social_guard_dev.data.UsageStatsRepository

class SocialGuardApp : Application() {
    // Single instance of repository
    val usageStatsRepository by lazy { UsageStatsRepository(applicationContext) }
}