package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.repository.SubjectRepository
import androidx.lifecycle.ViewModel
import com.example.myapplication.Subject
import androidx.lifecycle.MutableLiveData

class SubjectViewModel : ViewModel() {

     val repository = SubjectRepository()

    // LiveData to hold all subjects
     val allSubjectsLiveData = MutableLiveData<List<Subject>>()
    val allSubjects: LiveData<List<Subject>> get() = allSubjectsLiveData

    // LiveData for averages
     val averagesLiveData = MutableLiveData<Averages>()
    val averages: LiveData<Averages> get() = averagesLiveData

    init {
        // 초기화 시 모든 학기의 데이터를 가져와서 평균을 계산
        fetchAllSubjects()
    }

    // Function to fetch all subjects from the repository
    fun fetchAllSubjects() {
        repository.getAllSubjects { allSubjects ->
            allSubjectsLiveData.postValue(allSubjects)
            Log.d("SubjectViewModel", "All subjects fetched: $allSubjects")
            calculateAverages(allSubjects)
        }
    }

    // Function to calculate averages
    fun calculateAverages(allSubjects: List<Subject>?) {
        if (allSubjects.isNullOrEmpty()) {
            // Handle null or empty subjects list
            return
        }

        val totalCredits = allSubjects.sumOf { it.credits }

        val majorSubjects = allSubjects.filter { it.major == 1 }
        val generalSubjects = allSubjects.filter { it.major == 0 }

        val totalAverage = allSubjects.sumOf { it.grade * it.credits } / totalCredits
        val majorAverage = majorSubjects.sumOf { it.grade * it.credits } / majorSubjects.sumOf { it.credits }
        val generalAverage = generalSubjects.sumOf { it.grade * it.credits } / generalSubjects.sumOf { it.credits }

        // Update LiveData for averages
        averagesLiveData.postValue(Averages(totalAverage, majorAverage, generalAverage))
    }

    data class Averages(val totalAverage: Double, val majorAverage: Double, val generalAverage: Double)

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
    fun addSubjectToRepository(semesterNumber: Int, courseName: String, grade: Double, credits: Int, major:Int) {
        val subject = Subject(courseName, grade, credits,major)
        repository.addSubject(semesterNumber, subject)
    }
}