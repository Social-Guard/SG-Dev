package com.example.social_guard_dev.viewmodels

import android.app.Application
import android.app.usage.UsageStats
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.social_guard_dev.data.UsageStatsRepository
import kotlinx.coroutines.launch

class UsageViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UsageStatsRepository(application)

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _todayUsage = MutableLiveData<List<UsageStats>>()
    val todayUsage: LiveData<List<UsageStats>> = _todayUsage

    // Add these new LiveData properties
    private val _weeklyUsage = MutableLiveData<Map<String, Long>>()
    val weeklyUsage: LiveData<Map<String, Long>> = _weeklyUsage

    private val _dailyBreakdown = MutableLiveData<Map<String, List<Long>>>()
    val dailyBreakdown: LiveData<Map<String, List<Long>>> = _dailyBreakdown

    fun refreshData() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                _todayUsage.postValue(repository.getTodayUsage())
                _weeklyUsage.postValue(repository.getWeeklyUsage())
                _dailyBreakdown.postValue(repository.getDailyBreakdownForWeek())
            } catch (e: SecurityException) {
                _error.postValue("Usage access permission required")
            } catch (e: Exception) {
                _error.postValue("Failed to load usage data: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun getAppName(packageName: String): String {
        return repository.getAppName(packageName)
    }
}
