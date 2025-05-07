package com.example.social_guard_dev.ui.achievements

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.social_guard_dev.R
import com.example.social_guard_dev.ui.data.Achievement
import com.example.social_guard_dev.databinding.DialogAchievementDetailBinding

class AchievementDetailDialog : DialogFragment() {
    companion object {
        fun newInstance(achievement: Achievement): AchievementDetailDialog {
            return AchievementDetailDialog().apply {
                arguments = Bundle().apply {
                    putParcelable("achievement", achievement)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val achievement = arguments?.getParcelable<Achievement>("achievement")!!
        val binding = DialogAchievementDetailBinding.inflate(layoutInflater)

        binding.apply {
            achievementIcon.setImageResource(achievement.iconResId)
            achievementName.text = achievement.name
            achievementDescription.text = achievement.description
            progressText.text = achievement.progressText()
            progressBar.max = achievement.target
            progressBar.progress = achievement.progress
            pointsText.text = "${achievement.rewardPoints} pts"

            if (achievement.achieved) {
                statusText.text = "Achieved!"
                statusText.setTextColor(
                    androidx.core.content.ContextCompat.getColor(
                        requireContext(),
                        R.color.success
                    )
                )
            } else {
                statusText.text = "In Progress"
                statusText.setTextColor(
                    androidx.core.content.ContextCompat.getColor(
                        requireContext(),
                        R.color.warning
                    )
                )
            }
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton("OK", null)
            .create()
    }
}