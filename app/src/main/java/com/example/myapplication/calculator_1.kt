package com.example.myapplication
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentCalculator1Binding
import com.example.myapplication.viewmodel.SubjectViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class calculator_1 : Fragment() {
    private lateinit var binding: FragmentCalculator1Binding
    private val viewModel: SubjectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val view = binding.root



        observeLiveData()

        binding.btnRefresh.setOnClickListener {
            viewModel.fetchAllSubjects()
        }

        binding.mainButton1.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun setupChart(chart: BarChart, totalAverage: Double, majorAverage: Double, generalAverage: Double) {
        chart.setTouchEnabled(true)
        chart.description.isEnabled = false
        chart.legend.isEnabled = true

        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(0f, totalAverage.toFloat()))
        barEntries.add(BarEntry(1f, majorAverage.toFloat()))
        barEntries.add(BarEntry(2f, generalAverage.toFloat()))

        val dataSet = BarDataSet(barEntries, "Averages")
        dataSet.colors = listOf(ColorTemplate.COLORFUL_COLORS[0], ColorTemplate.COLORFUL_COLORS[1], ColorTemplate.COLORFUL_COLORS[2])

        val data = BarData(dataSet)
        chart.data = data

        val labels = arrayOf("Total Average", "Major Average", "General Average")
        chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)
        chart.xAxis.granularity = 1f
        chart.xAxis.isGranularityEnabled = true

        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false

        chart.animateY(1000)
        chart.invalidate() // Refresh the chart
    }




    private fun observeLiveData() {
        viewModel.averages.observe(viewLifecycleOwner) { averages ->
            updateTextViews(averages)
            setupChart(binding.chart, averages.totalAverage, averages.majorAverage, averages.generalAverage)
        }
    }




    private fun updateTextViews(averages: SubjectViewModel.Averages) {
        binding.txtTotalAverage.text = String.format("%.2f", averages.totalAverage)
        binding.txtMajorAverage.text = String.format("%.2f", averages.majorAverage)
        binding.txtGeneralAverage.text = String.format("%.2f", averages.generalAverage)
    }


}
