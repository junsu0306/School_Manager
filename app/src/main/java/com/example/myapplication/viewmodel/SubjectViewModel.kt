package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myapplication.repository.SubjectRepository
import androidx.lifecycle.ViewModel
import com.example.myapplication.Subject
import androidx.lifecycle.MutableLiveData

class SubjectViewModel : ViewModel() {

    // SubjectRepository 인스턴스를 초기화합니다.
    private val repository = SubjectRepository()

    // 모든 과목을 담을 LiveData를 초기화합니다.
    private val allSubjectsLiveData = MutableLiveData<List<Subject>>()
    val allSubjects: LiveData<List<Subject>> get() = allSubjectsLiveData

    // 평균 점수를 담을 LiveData를 초기화합니다.
    private val averagesLiveData = MutableLiveData<Averages>()
    val averages: LiveData<Averages> get() = averagesLiveData

    init {
        // ViewModel이 생성될 때, 모든 과목 데이터를 가져오고 평균을 계산합니다.
        fetchAllSubjects()
    }

    // 모든 과목을 Repository에서 가져오는 함수입니다.
    fun fetchAllSubjects() {
        repository.getAllSubjects { allSubjects ->
            allSubjectsLiveData.postValue(allSubjects)
            Log.d("SubjectViewModel", "All subjects fetched: $allSubjects")
            calculateAverages(allSubjects)
        }
    }

    // 전체, 전공, 일반 과목의 평균 점수를 계산하는 함수입니다.
    fun calculateAverages(allSubjects: List<Subject>?) {
        if (allSubjects.isNullOrEmpty()) {
            return
        }

        val totalCredits = allSubjects.sumOf { it.credits }
        val majorSubjects = allSubjects.filter { it.major == 1 }
        val generalSubjects = allSubjects.filter { it.major == 0 }

        val totalAverage = allSubjects.sumOf { it.grade * it.credits } / totalCredits
        val majorAverage = majorSubjects.sumOf { it.grade * it.credits } / majorSubjects.sumOf { it.credits }
        val generalAverage = generalSubjects.sumOf { it.grade * it.credits } / generalSubjects.sumOf { it.credits }

        averagesLiveData.postValue(Averages(totalAverage, majorAverage, generalAverage))
    }

    // 각 학기별 평균 점수를 담을 LiveData입니다.
    private val semesterAveragesLiveData = MutableLiveData<List<Double>>()
    val semesterAverages: LiveData<List<Double>> get() = semesterAveragesLiveData

    // 평균 점수를 저장하는 데이터 클래스입니다.
    data class Averages(val totalAverage: Double, val majorAverage: Double, val generalAverage: Double)

    // 과목 정보를 담을 LiveData를 초기화합니다.
    private val subjectsLiveData = MutableLiveData<List<Subject>>()
    val subjects: LiveData<List<Subject>> get() = subjectsLiveData

    // 특정 학기의 과목 정보를 Repository에서 가져오는 함수입니다.
    fun fetchSubjectsFromRepository(semesterNumber: Int) {
        repository.getSubjects(semesterNumber) { subjects ->
            subjectsLiveData.postValue(subjects)
        }
    }

    // Repository에 새 과목을 추가하는 함수입니다.
    fun addSubjectToRepository(semesterNumber: Int, courseName: String, grade: Double, credits: Int, major:Int) {
        val subject = Subject(courseName, grade, credits, major)
        repository.addSubject(semesterNumber, subject)
    }
}
