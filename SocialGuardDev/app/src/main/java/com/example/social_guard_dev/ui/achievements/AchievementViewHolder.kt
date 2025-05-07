package com.example.social_guard_dev.ui.achievements

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.social_guard_dev.ui.data.Achievement
import com.example.social_guard_dev.databinding.ItemAchievementBinding
import com.example.social_guard_dev.R

class AchievementViewHolder(
    private val binding: ItemAchievementBinding,
    private val onClick: (Achievement) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(achievement: Achievement) {
        binding.apply {
            achievementName.text = achievement.name
            achievementDescription.text = achievement.description
            achievementIcon.setImageResource(achievement.iconResId)
            progressText.text = achievement.progressText()
            progressBar.max = achievement.target
            progressBar.progress = achievement.progress

            // Visual states
            when {
                achievement.achieved -> {
                    root.alpha = 1f
                    achievementIcon.alpha = 1f
                    progressBar.progressDrawable =
                        ContextCompat.getDrawable(root.context, R.drawable.progress_achieved)
                }
                achievement.isInProgress() -> {
                    root.alpha = 0.8f
                    achievementIcon.alpha = 0.8f
                    progressBar.progressDrawable =
                        ContextCompat.getDrawable(root.context, R.drawable.progress_in_progress)
                }
                else -> {
                    root.alpha = 0.5f
                    achievementIcon.alpha = 0.5f
                    progressBar.progressDrawable =
                        ContextCompat.getDrawable(root.context, R.drawable.progress_locked)
                }
            }

            root.setOnClickListener { onClick(achievement) }
        }
    }
}