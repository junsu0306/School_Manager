package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentCalculator1Binding
import com.example.myapplication.viewmodel.SubjectViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class calculator_1 : Fragment() {
    // 바인딩 객체를 초기화합니다. 이 객체를 통해 레이아웃의 뷰에 접근할 수 있습니다.
    private lateinit var binding: FragmentCalculator1Binding

    // ViewModel 객체를 지연 초기화합니다. ViewModel의 데이터를 관찰하고 UI를 업데이트합니다.
    private val viewModel: SubjectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃을 인플레이트하여 바인딩 객체를 초기화합니다.
        binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val view = binding.root

        // LiveData를 관찰하여 데이터 변경시 UI를 업데이트하는 함수를 호출합니다.
        observeLiveData()

        // '새로고침' 버튼에 클릭 리스너를 설정하여 과목 데이터를 새로고침합니다.
        binding.btnRefresh.setOnClickListener {
            viewModel.fetchAllSubjects()
        }

        // 메인 액티비티로 돌아가는 버튼에 클릭 리스너를 설정합니다.
        binding.mainButton1.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    // 막대 차트를 설정하는 함수입니다.
    private fun setupChart(chart: BarChart, totalAverage: Double, majorAverage: Double, generalAverage: Double) {
        // 차트의 설정을 정의합니다.
        chart.setTouchEnabled(true)
        chart.description.isEnabled = false
        chart.legend.isEnabled = true

        // 막대 데이터를 생성합니다.
        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(0f, totalAverage.toFloat()))
        barEntries.add(BarEntry(1f, majorAverage.toFloat()))
        barEntries.add(BarEntry(2f, generalAverage.toFloat()))

        // 데이터 세트를 생성하고 차트에 설정합니다.
        val dataSet = BarDataSet(barEntries, "Averages")
        dataSet.colors = listOf(ColorTemplate.COLORFUL_COLORS[0], ColorTemplate.COLORFUL_COLORS[1], ColorTemplate.COLORFUL_COLORS[2])
        val data = BarData(dataSet)
        chart.data = data

        // X축 레이블을 설정합니다.
        val labels = arrayOf("Total Average", "Major Average", "General Average")
        chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)
        chart.xAxis.granularity = 1f
        chart.xAxis.isGranularityEnabled = true

        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled = false

        chart.animateY(1000) // 차트 애니메이션 효과
        chart.invalidate() // 차트 새로고침
    }

    // ViewModel의 LiveData를 관찰하고 UI를 업데이트하는 함수입니다.
    private fun observeLiveData() {
        viewModel.averages.observe(viewLifecycleOwner) { averages ->
            updateTextViews(averages)
            setupChart(binding.chart, averages.totalAverage, averages.majorAverage, averages.generalAverage)
        }
    }

    // TextView를 업데이트하는 함수입니다.
    private fun updateTextViews(averages: SubjectViewModel.Averages) {
        binding.txtTotalAverage.text = String.format("%.2f", averages.totalAverage)
        binding.txtMajorAverage.text = String.format("%.2f", averages.majorAverage)
        binding.txtGeneralAverage.text = String.format("%.2f", averages.generalAverage)
    }
}
