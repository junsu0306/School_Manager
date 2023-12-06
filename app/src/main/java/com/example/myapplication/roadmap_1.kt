package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class roadmap_1 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_roadmap_1, container, false)

        val btnSubject = view.findViewById<Button>(R.id.btn_subject)
        btnSubject.setOnClickListener {
            // 네비게이션을 통해 roadmap_2 Fragment로 이동
            findNavController().navigate(R.id.action_roadmap_1_to_roadmap_2)
        }

        return view
    }
    }
