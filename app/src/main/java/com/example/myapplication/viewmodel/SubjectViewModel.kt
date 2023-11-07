package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import com.example.myapplication.repository.SubjectRepository
import androidx.lifecycle.ViewModel
import com.example.myapplication.Subject
import androidx.lifecycle.MutableLiveData


class SubjectViewModel : ViewModel() {
    private val repository = SubjectRepository()

    private val _subject = MutableLiveData<ArrayList<Subject>>("UNCHECKED_STRING")
    val subject : LiveData<String> get()= _subject


    var courseName: String = ""
    var grade: String = ""
    var credits: String = ""

    val subjects = repository.getSubjects()

    fun addSubject() {
        val courseName = this.courseName
        val grade = this.grade
        val credits = this.credits

        if (courseName.isNotBlank() && grade.isNotBlank() && credits.isNotBlank()) {
            val subject = Subject(courseName, grade, credits)
            repository.addSubject(subject)

            // 데이터 입력 후 필드 초기화
            this.courseName = ""
            this.grade = ""
            this.credits = ""
        }
    }

    fun updateSelectedSemester(semester: String) {
        selectedSemester = semester
    }



}
