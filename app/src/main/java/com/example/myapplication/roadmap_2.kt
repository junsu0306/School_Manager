package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentRoadmap2Binding

class roadmap_2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //해당 레이아웃 inflate하고 바인딩
        val binding = FragmentRoadmap2Binding.inflate(inflater, container, false)

        val subjectFragment = SubjectRecyclerviewFragment() // SubjectRecyclerviewFragment 인스턴스 생성
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // SubjectRecyclerviewFragment를 roadmap_2 Fragment에 추가
        transaction.replace(binding.listFrag.id, subjectFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        return binding.root
    }
}