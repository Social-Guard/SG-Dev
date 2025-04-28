package com.example.social_guard_dev.ui.achievements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.social_guard_dev.ui.data.Achievement
import com.example.social_guard_dev.databinding.ItemAchievementBinding

class AchievementsAdapter(
    private var achievements: List<Achievement>,
    private val onClick: (Achievement) -> Unit
) : RecyclerView.Adapter<AchievementViewHolder>() {

    fun updateData(newAchievements: List<Achievement>) {
        achievements = newAchievements
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val binding = ItemAchievementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AchievementViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        holder.bind(achievements[position])
    }

    override fun getItemCount() = achievements.size
}