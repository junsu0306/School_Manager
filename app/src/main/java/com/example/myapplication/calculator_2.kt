package com.example.myapplication

import SemesterAdapter
import SubjectAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


        // RecyclerView 초기화
        val recyclerView: RecyclerView = binding.recyclerView
        val subjectAdapter = SubjectAdapter()
        recyclerView.adapter = subjectAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // subjects LiveData를 Observer로 등록하여 데이터가 업데이트될 때마다 RecyclerView 갱신
        viewModel.subjects.observe(viewLifecycleOwner) { subjects ->
            subjectAdapter.submitList(subjects)
        }

        // 앱이 처음 실행될 때 Firebase에서 데이터를 불러와서 RecyclerView에 초기값으로 출력
        val initialSemesterNumber = 1 // 예시로 1학기의 데이터를 초기값으로 사용
        viewModel.fetchSubjectsFromRepository(initialSemesterNumber)



        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}