package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentSubjectRecyclerviewBinding

class SubjectRecyclerviewFragment : Fragment() {

    //private var param1: String? = null
    //private var param2: String? = null

    val subjects = arrayOf(
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고"),
        SubjectInfo("객체지향프로그래밍", "2학년", "이 과목은 어쩌고")
    )

    lateinit var binding : FragmentSubjectRecyclerviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        } */
        binding = FragmentSubjectRecyclerviewBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        binding.recSubjects.layoutManager = LinearLayoutManager(context)
        binding.recSubjects.adapter = SubjectsAdapter(subjects)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject_recyclerview, container, false)
    }

    /* companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubjectRecyclerviewFragment().apply {
                arguments = Bundle().apply {
                    putString(com.example.myapplication.viewmodel.ARG_PARAM1, param1)
                    putString(com.example.myapplication.viewmodel.ARG_PARAM2, param2)
                }
            }
    }*/
}