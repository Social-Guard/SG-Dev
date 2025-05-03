package com.example.social_guard_dev.ui.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social_guard_dev.ui.data.Achievement
import com.example.social_guard_dev.databinding.FragmentAchievementsBinding
import com.example.social_guard_dev.ui.viewmodels.AchievementsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AchievementsFragment : Fragment() {
    private var _binding: FragmentAchievementsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AchievementsViewModel by viewModels()
    private lateinit var achievementsAdapter: AchievementsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        viewModel.loadAchievements()
    }

    private fun setupRecyclerView() {
        achievementsAdapter = AchievementsAdapter(emptyList()) { achievement ->
            showAchievementDetails(achievement)
        }

        binding.achievementsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = achievementsAdapter
        }
    }

    private fun setupObservers() {
        viewModel.achievements.observe(viewLifecycleOwner) { achievements ->
            achievementsAdapter.updateData(achievements)
            updateSummaryViews()
        }
    }

    private fun updateSummaryViews() {
        binding.totalPointsText.text = viewModel.getTotalPoints().toString()
        binding.achievedCountText.text = viewModel.getAchievedCount().toString()
        binding.totalCountText.text = viewModel.getTotalCount().toString()
    }

    private fun showAchievementDetails(achievement: Achievement) {
        AchievementDetailDialog.newInstance(achievement)
            .show(parentFragmentManager, "achievement_detail")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
