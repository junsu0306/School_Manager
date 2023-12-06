package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListSubjectsBinding

class SubjectsAdapter(val subjects: Array<SubjectInfo>)
    : RecyclerView.Adapter<SubjectsAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListSubjectsBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun getItemCount() = subjects.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(subjects[position])
    }
    class Holder(private val binding: ListSubjectsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: SubjectInfo) {
            binding.txtName.text = subject.name
            binding.txtGrade.text = subject.grade
            binding.txtInfo.text = subject.info
        }
    }
}
