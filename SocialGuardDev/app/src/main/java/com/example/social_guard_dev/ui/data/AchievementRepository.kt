package com.example.social_guard_dev.ui.data

import android.content.Context
import androidx.core.content.edit
import com.example.social_guard_dev.R
import com.example.social_guard_dev.ui.data.Achievement

class AchievementRepository(private val context: Context) {
    private val prefs = context.getSharedPreferences("achievements", Context.MODE_PRIVATE)

    fun getAchievements(): List<Achievement> {
        return listOf(
            Achievement(
                id = "first_step",
                name = "First Step",
                description = "Reduce your app usage by 10%",
                iconResId = R.drawable.ic_achievement_first,
                progress = prefs.getInt("first_step_progress", 0),
                target = 10,
                achieved = prefs.getBoolean("first_step_achieved", false),
                type = Achievement.Type.USAGE_REDUCTION,
                rewardPoints = 10
            ),
            // Usage reduction achievements
            Achievement(
                id = "digital_novice",
                name = "Digital Novice",
                description = "Reduce your daily app usage by 15% for 3 consecutive days",
                iconResId = R.drawable.ic_achievement_novice,
                progress = prefs.getInt("digital_novice_progress", 0),
                target = 3,
                achieved = prefs.getBoolean("digital_novice_achieved", false),
                type = Achievement.Type.USAGE_REDUCTION_STREAK,
                rewardPoints = 20
            ),
            Achievement(
                id = "focused_user",
                name = "Focused User",
                description = "Keep daily usage under 2 hours for a week",
                iconResId = R.drawable.ic_achievement_focused,
                progress = prefs.getInt("focused_user_progress", 0),
                target = 7,
                achieved = prefs.getBoolean("focused_user_achieved", false),
                type = Achievement.Type.LOW_USAGE_STREAK,
                rewardPoints = 30
            ),
            Achievement(
                id = "social_guardian",
                name = "Social Guardian",
                description = "Reduce social media usage by 50% from your average",
                iconResId = R.drawable.ic_achievement_guardian,
                progress = prefs.getInt("social_guardian_progress", 0),
                target = 50, // Percentage
                achieved = prefs.getBoolean("social_guardian_achieved", false),
                type = Achievement.Type.SPECIFIC_APP_REDUCTION,
                rewardPoints = 40
            ),
            // Task completion achievements
            Achievement(
                id = "task_master",
                name = "Task Master",
                description = "Complete 10 tasks",
                iconResId = R.drawable.ic_achievement_task_master,
                progress = prefs.getInt("task_master_progress", 0),
                target = 10,
                achieved = prefs.getBoolean("task_master_achieved", false),
                type = Achievement.Type.TASK_COMPLETION,
                rewardPoints = 15
            ),
            Achievement(
                id = "early_bird",
                name = "Early Bird",
                description = "Complete 5 tasks before noon",
                iconResId = R.drawable.ic_achievement_early_bird,
                progress = prefs.getInt("early_bird_progress", 0),
                target = 5,
                achieved = prefs.getBoolean("early_bird_achieved", false),
                type = Achievement.Type.MORNING_TASKS,
                rewardPoints = 25
            ),
            Achievement(
                id = "streak_champion",
                name = "Streak Champion",
                description = "Complete at least 1 task for 7 days straight",
                iconResId = R.drawable.ic_achievement_streak,
                progress = prefs.getInt("streak_champion_progress", 0),
                target = 7,
                achieved = prefs.getBoolean("streak_champion_achieved", false),
                type = Achievement.Type.TASK_STREAK,
                rewardPoints = 35
            ),
            // Focus mode achievements
            Achievement(
                id = "focused_beginner",
                name = "Focused Beginner",
                description = "Complete your first focus session",
                iconResId = R.drawable.ic_achievement_focus_beginner,
                progress = prefs.getInt("focused_beginner_progress", 0),
                target = 1,
                achieved = prefs.getBoolean("focused_beginner_achieved", false),
                type = Achievement.Type.FOCUS_SESSION,
                rewardPoints = 10
            ),
            Achievement(
                id = "deep_work",
                name = "Deep Work",
                description = "Complete 5 focus sessions of 30+ minutes",
                iconResId = R.drawable.ic_achievement_deep_work,
                progress = prefs.getInt("deep_work_progress", 0),
                target = 5,
                achieved = prefs.getBoolean("deep_work_achieved", false),
                type = Achievement.Type.LONG_FOCUS_SESSIONS,
                rewardPoints = 30
            ),
            Achievement(
                id = "undisturbed",
                name = "Undisturbed",
                description = "Complete a focus session without interruptions",
                iconResId = R.drawable.ic_achievement_undisturbed,
                progress = prefs.getInt("undisturbed_progress", 0),
                target = 1,
                achieved = prefs.getBoolean("undisturbed_achieved", false),
                type = Achievement.Type.UNINTERRUPTED_SESSION,
                rewardPoints = 20
            ),
            // Consistency achievements
            Achievement(
                id = "weekly_hero",
                name = "Weekly Hero",
                description = "Meet all your usage goals for a week",
                iconResId = R.drawable.ic_achievement_weekly_hero,
                progress = prefs.getInt("weekly_hero_progress", 0),
                target = 7,
                achieved = prefs.getBoolean("weekly_hero_achieved", false),
                type = Achievement.Type.GOAL_STREAK,
                rewardPoints = 40
            ),
            Achievement(
                id = "monthly_champion",
                name = "Monthly Champion",
                description = "Stay within usage limits for 30 days",
                iconResId = R.drawable.ic_achievement_monthly,
                progress = prefs.getInt("monthly_champion_progress", 0),
                target = 30,
                achieved = prefs.getBoolean("monthly_champion_achieved", false),
                type = Achievement.Type.LONG_TERM_CONSISTENCY,
                rewardPoints = 50
            ),
            Achievement(
                id = "balanced_life",
                name = "Balanced Life",
                description = "Maintain balanced usage across all categories for a week",
                iconResId = R.drawable.ic_achievement_balanced,
                progress = prefs.getInt("balanced_life_progress", 0),
                target = 7,
                achieved = prefs.getBoolean("balanced_life_achieved", false),
                type = Achievement.Type.BALANCED_USAGE,
                rewardPoints = 35
            ),
            // Special challenge achievements
            Achievement(
                id = "digital_detox",
                name = "Digital Detox",
                description = "Go 24 hours without using restricted apps",
                iconResId = R.drawable.ic_achievement_detox,
                progress = prefs.getInt("digital_detox_progress", 0),
                target = 1,
                achieved = prefs.getBoolean("digital_detox_achieved", false),
                type = Achievement.Type.SPECIAL_CHALLENGE,
                rewardPoints = 50
            ),
            Achievement(
                id = "night_owl",
                name = "Night Owl",
                description = "Avoid device usage after 10 PM for a week",
                iconResId = R.drawable.ic_achievement_night_owl,
                progress = prefs.getInt("night_owl_progress", 0),
                target = 7,
                achieved = prefs.getBoolean("night_owl_achieved", false),
                type = Achievement.Type.NIGHT_TIME_DISCIPLINE,
                rewardPoints = 40
            ),
            Achievement(
                id = "morning_person",
                name = "Morning Person",
                description = "Don't use your phone for 1 hour after waking up for 5 days",
                iconResId = R.drawable.ic_achievement_morning,
                progress = prefs.getInt("morning_person_progress", 0),
                target = 5,
                achieved = prefs.getBoolean("morning_person_achieved", false),
                type = Achievement.Type.MORNING_DISCIPLINE,
                rewardPoints = 35
            ),
            // Milestone achievements
            Achievement(
                id = "first_goal",
                name = "First Goal",
                description = "Set your first app usage limit",
                iconResId = R.drawable.ic_achievement_first_goal,
                progress = prefs.getInt("first_goal_progress", 0),
                target = 1,
                achieved = prefs.getBoolean("first_goal_achieved", false),
                type = Achievement.Type.MILESTONE,
                rewardPoints = 10
            ),
            Achievement(
                id = "silver_user",
                name = "Silver User",
                description = "Earn 100 achievement points",
                iconResId = R.drawable.ic_achievement_silver,
                progress = prefs.getInt("silver_user_progress", 0),
                target = 100,
                achieved = prefs.getBoolean("silver_user_achieved", false),
                type = Achievement.Type.POINTS_MILESTONE,
                rewardPoints = 0
            ),
            Achievement(
                id = "gold_user",
                name = "Gold User",
                description = "Earn 500 achievement points",
                iconResId = R.drawable.ic_achievement_gold,
                progress = prefs.getInt("gold_user_progress", 0),
                target = 500,
                achieved = prefs.getBoolean("gold_user_achieved", false),
                type = Achievement.Type.POINTS_MILESTONE,
                rewardPoints = 0
            )
        )
    }

    fun getAchievementsByCategory(category: Achievement.Category): List<Achievement> {
        return getAchievements().filter { it.category == category }
    }

    fun updateAchievementProgress(id: String, progress: Int) {
        prefs.edit {
            putInt("${id}_progress", progress)
            val target = getAchievements().find { it.id == id }?.target ?: 0
            if (progress >= target) {
                putBoolean("${id}_achieved", true)
            }
        }
    }

    fun getTotalPoints(): Int {
        return getAchievements()
            .filter { it.achieved }
            .sumOf { it.rewardPoints }
    }
}