package com.example.myapplication

import SubjectAdapter
import android.content.Intent
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
import com.example.myapplication.databinding.FragmentCalculator2Binding
import com.example.myapplication.viewmodel.SubjectViewModel

class calculator_2 : Fragment() {
    // 바인딩 객체 초기화
    private lateinit var binding: FragmentCalculator2Binding

    // ViewModel 객체 지연 초기화
    private val viewModel: SubjectViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃을 인플레이트하여 바인딩 객체를 초기화합니다.
        binding = FragmentCalculator2Binding.inflate(inflater, container, false)
        val view = binding.root

        // 학기 목록을 초기화하고 스피너 어댑터를 설정합니다.
        val semesterArray = resources.getStringArray(R.array.semester_array)
        val semesterList = semesterArray.toList()
        val semesterAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, semesterList)
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnSemester.adapter = semesterAdapter

        // 스피너에서 학기를 선택할 때의 동작을 정의합니다.
        binding.spnSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedSemester = if (position == AdapterView.INVALID_POSITION) 1 else position + 1
                viewModel.fetchSubjectsFromRepository(selectedSemester)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무 것도 선택되지 않았을 때 동작
            }
        }

        // 과목 추가 버튼에 대한 동작을 정의합니다.
        binding.addButton.setOnClickListener {
            val courseName = binding.txtCourse.text.toString()
            val grade = binding.txtGrade.text.toString().toDouble()
            val credits = binding.txtCredit.text.toString().toInt()
            val major = binding.txtMajor.text.toString().toInt()
            val semesterNumber = binding.spnSemester.selectedItemPosition + 1

            viewModel.addSubjectToRepository(semesterNumber, courseName, grade, credits, major)
            binding.txtCourse.text.clear()
            binding.txtGrade.text.clear()
            binding.txtCredit.text.clear()
            binding.txtMajor.text.clear()
        }

        // 메인 액티비티로 돌아가는 버튼에 대한 동작을 정의합니다.
        binding.mainButton2.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        // RecyclerView를 초기화하고 어댑터를 설정합니다.
        val recyclerView: RecyclerView = binding.recyclerView
        val subjectAdapter = SubjectAdapter()
        recyclerView.adapter = subjectAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel의 subjects LiveData를 관찰하여 RecyclerView를 갱신합니다.
        viewModel.subjects.observe(viewLifecycleOwner) { subjects ->
            subjectAdapter.submitList(subjects)
        }

        // 앱이 처음 실행될 때 기본 학기 데이터를 불러옵니다.
        val initialSemesterNumber = 1
        viewModel.fetchSubjectsFromRepository(initialSemesterNumber)

        // '새로고침' 버튼에 대한 동작을 정의합니다.
        binding.Buttonrestart.setOnClickListener {
            val selectedSemester = binding.spnSemester.selectedItemPosition + 1
            viewModel.fetchSubjectsFromRepository(selectedSemester)
        }

        return view
    }
}
