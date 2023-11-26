package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentCalculator1Binding
import com.example.myapplication.viewmodel.SubjectViewModel

class Calculator1Fragment : Fragment() {
    private lateinit var binding: FragmentCalculator1Binding // 바인딩 변수
    private val viewModel: SubjectViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculator1Binding.inflate(inflater, container, false)
        val view = binding.root

        // Observe LiveData for average values
        viewModel.totalAverage.observe(viewLifecycleOwner) { totalAverage ->
            // Set total average to TextView in your layout
            binding.txtTotalAverage.text = String.format("%.2f", totalAverage)
        }

        viewModel.majorAverage.observe(viewLifecycleOwner) { majorAverage ->
            // Set major average to TextView in your layout
            binding.txtMajorAverage.text = String.format("%.2f", majorAverage)
        }

        viewModel.generalAverage.observe(viewLifecycleOwner) { generalAverage ->
            // Set general average to TextView in your layout
            binding.txtGeneralAverage.text = String.format("%.2f", generalAverage)
        }

        binding.btnRefresh.setOnClickListener {
            // 앱이 처음 실행될 때 Firebase에서 데이터를 불러와서 ViewModel에 설정
            val initialSemesterNumber = 1 // 예시로 1학기의 데이터를 초기값으로 사용
            viewModel.fetchSubjectsFromRepository(initialSemesterNumber)
        }

        return view
    }


}