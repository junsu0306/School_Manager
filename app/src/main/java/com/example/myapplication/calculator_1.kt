package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentCalculator1Binding
import com.example.myapplication.viewmodel.SubjectViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class calculator_1 : Fragment() {
    private lateinit var binding: FragmentCalculator1Binding
    private val viewModel: SubjectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val view = binding.root

        setupChart(binding.chart)

        observeLiveData()

        binding.btnRefresh.setOnClickListener {
            viewModel.fetchAllSubjects()
        }

        return view
    }

    private fun setupChart(chart: LineChart) {
        chart.setTouchEnabled(true)
        chart.description.isEnabled = false
        chart.legend.isEnabled = true
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.axisLeft.textColor = Color.BLACK
        chart.axisRight.isEnabled = false
    }

    private fun observeLiveData() {
        viewModel.averages.observe(viewLifecycleOwner) { averages ->
            updateChart(binding.chart, averages.totalAverage)
            updateTextViews(averages)
        }
    }

    private fun updateChart(chart: LineChart, totalAverage: Double) {
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, totalAverage.toFloat()))

        val dataSet = LineDataSet(entries, "Total Average")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate() // refresh
    }

    private fun updateTextViews(averages: SubjectViewModel.Averages) {
        binding.txtTotalAverage.text = String.format("%.2f", averages.totalAverage)
        binding.txtMajorAverage.text = String.format("%.2f", averages.majorAverage)
        binding.txtGeneralAverage.text = String.format("%.2f", averages.generalAverage)
    }
}
