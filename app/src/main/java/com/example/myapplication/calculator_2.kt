package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.PostListAdapter
import com.example.myapplication.viewmodel.SubjectViewModel
import com.example.myapplication.databinding.FragmentCalculator2Binding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class calculator_2 : Fragment() {
    private lateinit var binding: FragmentCalculator2Binding // 바인딩 변수

    private val viewModel: SubjectViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculator2Binding.inflate(inflater, container, false)
        val view = binding.root

        //버튼 눌렀을시 데이터 가져오기
        binding.addButton.setOnClickListener {
            val courseName = binding.txtCourse.text.toString()
            val grade = binding.txtGrade.text.toString().toDouble()
            val credits = binding.txtCredit.text.toString().toDouble()

            val semesterNumber = 1 // 학기 번호를 설정하세요

            // SubjectViewModel을 통해 Firebase에 데이터 추가
            viewModel.addSubjectToRepository(semesterNumber, courseName, grade, credits)
            binding.txtCourse.text.clear()
            binding.txtGrade.text.clear()
            binding.txtCredit.text.clear()



        }





        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


}