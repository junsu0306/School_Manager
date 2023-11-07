package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.repository.SubjectRepository
import androidx.lifecycle.ViewModel
import com.example.myapplication.Subject
import androidx.lifecycle.MutableLiveData


class SubjectViewModel : ViewModel() {
    private val repository = SubjectRepository()

    // LiveData to hold the subjects
    private val subjectsLiveData = MutableLiveData<List<Subject>>()
    val subjects: LiveData<List<Subject>> get() = subjectsLiveData

    // Function to fetch subjects from the repository
    fun fetchSubjectsFromRepository(semesterNumber: Int) {
        repository.getSubjects(semesterNumber) { subjects ->
            subjectsLiveData.postValue(subjects)
        }
    }

    // Function to add a subject to the repository
    fun addSubjectToRepository(semesterNumber: Int, courseName: String, grade: Double, credits: Double) {
        val subject = Subject(courseName, grade, credits)
        repository.addSubject(semesterNumber, subject)
    }
}
