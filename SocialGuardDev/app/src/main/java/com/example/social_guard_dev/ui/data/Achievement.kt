package com.example.social_guard_dev.ui.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Achievement(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val iconResId: Int,
    val progress: Int = 0,
    val target: Int,
    val achieved: Boolean = false,
    val type: Type,
    val rewardPoints: Int = 0,
    val category: Category = Category.GENERAL
) : Parcelable {
    enum class Type {
        USAGE_REDUCTION,
        USAGE_REDUCTION_STREAK,
        LOW_USAGE_STREAK,
        SPECIFIC_APP_REDUCTION,
        TASK_COMPLETION,
        MORNING_TASKS,
        TASK_STREAK,
        FOCUS_SESSION,
        LONG_FOCUS_SESSIONS,
        UNINTERRUPTED_SESSION,
        GOAL_STREAK,
        LONG_TERM_CONSISTENCY,
        BALANCED_USAGE,
        SPECIAL_CHALLENGE,
        NIGHT_TIME_DISCIPLINE,
        MORNING_DISCIPLINE,
        MILESTONE,
        POINTS_MILESTONE
    }

    enum class Category {
        GENERAL,
        USAGE,
        TASKS,
        FOCUS,
        CONSISTENCY,
        CHALLENGE,
        MILESTONE
    }

    fun progressPercentage(): Int = if (target > 0) (progress * 100 / target).coerceAtMost(100) else 0

    fun isInProgress(): Boolean = progress > 0 && !achieved

    fun progressText(): String = when (type) {
        Type.MILESTONE -> "$progress/$target Points"
        else -> "$progress/$target"
    }
}
