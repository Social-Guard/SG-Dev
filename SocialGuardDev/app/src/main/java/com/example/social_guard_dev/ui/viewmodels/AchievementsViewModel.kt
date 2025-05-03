package com.example.social_guard_dev.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.social_guard_dev.ui.data.Achievement
import com.example.social_guard_dev.ui.data.AchievementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    application: Application,
    private val repository: AchievementRepository
) : AndroidViewModel(application) {

    private val _achievements = MutableLiveData<List<Achievement>>()
    val achievements: LiveData<List<Achievement>> = _achievements

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadAchievements()
    }

    fun loadAchievements() {
        _isLoading.value = true
        _error.value = null

        try {
            _achievements.value = repository.getAchievements()
        } catch (e: Exception) {
            _error.value = "Failed to load achievements: ${e.message}"
        } finally {
            _isLoading.value = false
        }
    }


    fun getAchievementsByCategory(category: Achievement.Category): List<Achievement> {
        return repository.getAchievementsByCategory(category)
    }

    fun getTotalPoints(): Int {
        return repository.getTotalPoints()
    }

    fun getAchievedCount(): Int {
        return _achievements.value?.count { it.achieved } ?: 0
    }

    fun getTotalCount(): Int {
        return _achievements.value?.size ?: 0
    }
}