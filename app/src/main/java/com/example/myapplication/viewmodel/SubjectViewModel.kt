package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.repository.SubjectRepository
import androidx.lifecycle.ViewModel
import com.example.myapplication.Subject
import androidx.lifecycle.MutableLiveData

class SubjectViewModel : ViewModel() {
    private val repository = SubjectRepository()
    val totalAverageLiveData = MutableLiveData<Double>()
    val majorAverageLiveData = MutableLiveData<Double>()
    val generalAverageLiveData = MutableLiveData<Double>()

    val totalAverage: LiveData<Double> get() = totalAverageLiveData
    val majorAverage: LiveData<Double> get() = majorAverageLiveData
    val generalAverage: LiveData<Double> get() = generalAverageLiveData
    // LiveData to hold all subjects
    private val allSubjectsLiveData = MutableLiveData<List<Subject>>()
    val allSubjects: LiveData<List<Subject>> get() = allSubjectsLiveData

    // Function to fetch all subjects from the repository
    fun fetchAllSubjects() {
        repository.getAllSubjects { allSubjects ->
            allSubjectsLiveData.postValue(allSubjects)
        }
    }

    // Function to calculate averages
    fun calculateAverages() {
        val allSubjects = allSubjectsLiveData.value
        if (allSubjects != null && allSubjects.isNotEmpty()) {
            val totalCredits = allSubjects.sumOf { it.credits }

            val majorSubjects = allSubjects.filter { it.major == 1.0 }
            val generalSubjects = allSubjects.filter { it.major == 0.0 }

            val totalAverage = allSubjects.sumOf { it.grade * it.credits } / totalCredits
            val majorAverage = majorSubjects.sumOf { it.grade * it.credits } / majorSubjects.sumOf { it.credits }
            val generalAverage = generalSubjects.sumOf { it.grade * it.credits } / generalSubjects.sumOf { it.credits }

            totalAverageLiveData.postValue(totalAverage)
            majorAverageLiveData.postValue(majorAverage)
            generalAverageLiveData.postValue(generalAverage)
        }
    }

    init {
        // 초기화 시 모든 학기의 데이터를 가져와서 평균을 계산
        fetchAllSubjects()
        calculateAverages()
    }

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
    fun addSubjectToRepository(semesterNumber: Int, courseName: String, grade: Double, credits: Double, major:Double) {
        val subject = Subject(courseName, grade, credits,major)
        repository.addSubject(semesterNumber, subject)
    }
}