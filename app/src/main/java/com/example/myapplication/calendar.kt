package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class calendar : Fragment() {

    private lateinit var monthYearText: TextView
    private lateinit var recyclerView: RecyclerView

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        monthYearText = view.findViewById(R.id.monthYearText)
        recyclerView = view.findViewById(R.id.recyclerView)

        updateMonthYearText()

        recyclerView.layoutManager = LinearLayoutManager(context)

        view.findViewById<TextView>(R.id.pre_btn).setOnClickListener {
            adjustMonth(-1)
        }

        view.findViewById<TextView>(R.id.next_btn).setOnClickListener {
            adjustMonth(1)
        }

        return view
    }

    private fun adjustMonth(offset: Int) {
        calendar.add(Calendar.MONTH, offset)
        updateMonthYearText()
    }

    private fun updateMonthYearText() {
        val dateFormat = SimpleDateFormat("MM월 yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        monthYearText.text = formattedDate
    }
}
