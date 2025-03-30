package com.example.social_guard_dev.ui.trends

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.social_guard_dev.R
import com.example.social_guard_dev.databinding.FragmentTrendsBinding
import com.example.social_guard_dev.viewmodels.UsageViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter

class TrendsFragment : Fragment() {

    private var _binding: FragmentTrendsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dailyBreakdown.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                setupDailyChart(binding.dailyChart, data)
                binding.dailyChart.visibility = View.VISIBLE
                binding.emptyDailyView.visibility = View.GONE
            } else {
                binding.dailyChart.visibility = View.GONE
                binding.emptyDailyView.visibility = View.VISIBLE
            }
        }

        viewModel.weeklyUsage.observe(viewLifecycleOwner) { data ->
            if (data.isNotEmpty()) {
                setupWeeklyChart(binding.weeklyChart, data)
                binding.weeklyChart.visibility = View.VISIBLE
                binding.emptyWeeklyView.visibility = View.GONE
            } else {
                binding.weeklyChart.visibility = View.GONE
                binding.emptyWeeklyView.visibility = View.VISIBLE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.refreshData()
    }

    private fun setupDailyChart(chart: BarChart, data: Map<String, List<Long>>) {
        val topApps = data.entries.sortedByDescending { it.value.sum() }.take(3)
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        val entries = ArrayList<BarEntry>()
        topApps.forEachIndexed { appIndex, (appName, dailyData) ->
            dailyData.forEachIndexed { dayIndex, usage ->
                entries.add(BarEntry(
                    dayIndex.toFloat() + appIndex * 0.3f,
                    usage.toFloat() / 60000, // Convert to minutes
                    appName
                ))
            }
        }

        val dataSet = BarDataSet(entries, "Daily Usage (minutes)").apply {
            colors = listOf(Color.BLUE, Color.GREEN, Color.RED)
            valueFormatter = LargeValueFormatter("m")
            valueTextSize = 10f
        }

        chart.apply {
            this.data = BarData(dataSet).apply {
                barWidth = 0.25f
            }
            xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(days)
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                axisMinimum = -0.5f
                axisMaximum = 6.5f
            }
            axisLeft.apply {
                axisMinimum = 0f
                valueFormatter = LargeValueFormatter("m")
            }
            axisRight.isEnabled = false
            description.isEnabled = false
            legend.isEnabled = true
            setFitBars(true)
            animateY(1000)
            invalidate()
        }
    }

    private fun setupWeeklyChart(chart: LineChart, data: Map<String, Long>) {
        val sortedData = data.entries.sortedByDescending { it.value }.take(5)
        val entries = sortedData.mapIndexed { index, entry ->
            Entry(index.toFloat(), entry.value.toFloat() / 3600000) // Convert to hours
        }

        val dataSet = LineDataSet(entries, "Weekly Usage (hours)").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            lineWidth = 2f
            setCircleColor(Color.BLUE)
            valueFormatter = LargeValueFormatter("h")
        }

        chart.apply {
            this.data = LineData(dataSet)
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                isGranularityEnabled = true
                setDrawLabels(false)
            }
            axisLeft.apply {
                axisMinimum = 0f
                valueFormatter = LargeValueFormatter("h")
            }
            axisRight.isEnabled = false
            description.isEnabled = false
            legend.isEnabled = true
            animateX(1000)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}