package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.example.myapplication.databinding.FragmentCalculator1Binding
import com.github.mikephil.charting.components.YAxis
import com.example.myapplication.repository.SubjectRepository

class calculator_1 : Fragment() {
    private lateinit var binding: FragmentCalculator1Binding // 바인딩 변수
    private val repository = SubjectRepository()
    private var dataSets: MutableList<ILineDataSet> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val view = binding.root

        // 그래프 초기화
        setupChart(binding.chart)

        // Fetch all subjects and calculate averages when the fragment is created
        fetchAndCalculateAverages()

        binding.btnRefresh.setOnClickListener {
            // Fetch all subjects and calculate averages when the button is clicked
            fetchAndCalculateAverages()
        }

        return view
    }

    private fun setupChart(chart: LineChart) {
        // 그래프 설정 초기화
        chart.setTouchEnabled(false)
        chart.description.isEnabled = false
        chart.legend.isEnabled = true
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }

    private fun updateChart(chart: LineChart, totalAverage: Double, majorAverage: Double, generalAverage: Double) {
        // Use the Entry class to represent each point on the chart
        val totalEntry = Entry(chart.data.getDataSetByIndex(0).entryCount.toFloat(),
            totalAverage.toFloat()
        )
        val majorEntry = Entry(chart.data.getDataSetByIndex(1).entryCount.toFloat(),
            majorAverage.toFloat()
        )
        val generalEntry = Entry(chart.data.getDataSetByIndex(2).entryCount.toFloat(),
            generalAverage.toFloat()
        )

        // Add the entries to their respective datasets
        chart.data.getDataSetByIndex(0).addEntry(totalEntry)
        chart.data.getDataSetByIndex(1).addEntry(majorEntry)
        chart.data.getDataSetByIndex(2).addEntry(generalEntry)

        // Notify the chart that the data has changed
        chart.data.notifyDataChanged()
        chart.notifyDataSetChanged()
        chart.invalidate()
    }

    private fun createSet(label: String, color: Int): LineDataSet {
        val set = LineDataSet(null, label)
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.color = color
        set.setCircleColor(color)
        set.lineWidth = 2f
        set.circleRadius = 3f
        set.fillAlpha = 65
        set.fillColor = color
        set.highLightColor = Color.rgb(244, 117, 117)
        set.valueTextColor = color
        set.valueTextSize = 9f
        set.setDrawValues(true)
        return set
    }

    private fun fetchAndCalculateAverages() {
        // Fetch all subjects from the repository
        repository.getAllSubjects { allSubjects ->
            // Calculate averages
            calculateAverages(allSubjects)
        }
    }

    private fun calculateAverages(allSubjects: List<Subject>) {


        val totalCredits = allSubjects.sumOf { it.credits }

        val majorSubjects = allSubjects.filter { it.major == 1 }
        val generalSubjects = allSubjects.filter { it.major == 0 }

        val totalAverage = allSubjects.sumOf { it.grade * it.credits } / totalCredits
        val majorAverage = majorSubjects.sumOf { it.grade * it.credits } / majorSubjects.sumOf { it.credits }
        val generalAverage = generalSubjects.sumOf { it.grade * it.credits } / generalSubjects.sumOf { it.credits }

        // Update UI or perform any other actions with the averages
        // Note: You can define these variables at the class level for easier access
        // and update them in the calculateAverages method.
        binding.txtTotalAverage.text = String.format("%.2f", totalAverage)
        binding.txtMajorAverage.text = String.format("%.2f", majorAverage)
        binding.txtGeneralAverage.text = String.format("%.2f", generalAverage)

        // Update the chart with the new averages
        updateChart(binding.chart, totalAverage, majorAverage, generalAverage)
    }
}
