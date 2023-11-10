package com.example.myapplication

import SemesterAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Semester
import com.example.myapplication.viewmodel.SubjectViewModel
import com.example.myapplication.databinding.FragmentCalculator2Binding


class calculator_2 : Fragment() {
    private lateinit var binding: FragmentCalculator2Binding // 바인딩 변수

    private val viewModel: SubjectViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculator2Binding.inflate(inflater, container, false)
        val view = binding.root


        // 학기 목록 초기화
        val semesterArray = resources.getStringArray(R.array.semester_array)
        val semesterList = semesterArray.toList()

        // 스피너 어댑터 설정
        val semesterAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, semesterList)
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnSemester.adapter = semesterAdapter

        // 스피너 아이템 선택 리스너
        binding.spnSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // 학기 선택 시 동작
                val selectedSemester = if (position == AdapterView.INVALID_POSITION) {
                    // 아무 학기도 선택되지 않았을 때
                    "1"
                } else {
                    // 선택된 학기로 원하는 동작 수행
                    semesterList[position]
                }

                // 선택된 학기 정보 사용
                // 예: Logcat에 출력
                println("Selected Semester: $selectedSemester")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무 것도 선택되지 않았을 때의 동작
            }
        }


        //버튼 눌렀을시 데이터 가져오기
        binding.addButton.setOnClickListener {
            val courseName = binding.txtCourse.text.toString()
            val grade = binding.txtGrade.text.toString().toDouble()
            val credits = binding.txtCredit.text.toString().toDouble()
            val major = binding.txtMajor.text.toString().toDouble()
            val semesterNumber = binding.spnSemester.selectedItemPosition + 1

            // SubjectViewModel을 통해 Firebase에 데이터 추가
            viewModel.addSubjectToRepository(semesterNumber, courseName, grade, credits, major)
            binding.txtCourse.text.clear()
            binding.txtGrade.text.clear()
            binding.txtCredit.text.clear()
            binding.txtCredit.text.clear()


        }





        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}