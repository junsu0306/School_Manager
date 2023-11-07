package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.SubjectRepository


data class Course(val courseName: String, val grade: String, val credits: Int)

class Subject_viewmodel {
    private val subjRepository = SubjectRepository()

    // 데이터를 캡슐화하여 외부(뷰)에서 접근할 수 없도록하고
    // 외부 접근 프로퍼티는 immutable 타입으로 제한해 변경할 수 없도록 한다.
g
}