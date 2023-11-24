package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEntryBinding

class entryFragment : Fragment() {

    var binding: FragmentEntryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater)

        //바인딩 후 각 버튼 눌렀을때 어떤 액션을 취하는지 세팅해야함! onViewCreated에서 해결하는 것이 안전함

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnFragcalendar?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_calendar)
        }

        binding?.btnFragcalculator1?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_calculator_1)
        }

        binding?.btnFragcalculator2?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_calculator_2)
        }

        binding?.btnFragroadmap1?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_roadmap_1)
        }

        binding?.btnFragroadmap2?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_roadmap_2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}