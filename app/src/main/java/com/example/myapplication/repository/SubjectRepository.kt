package com.example.myapplication.repository

import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.example.myapplication.Subject
import com.google.firebase.database.ktx.database

class SubjectRepository {
    // Firebase 데이터베이스 인스턴스 초기화
    private val database: FirebaseDatabase = Firebase.database
    // 'subjects' 노드 참조 초기화
    private val subjectsReference: DatabaseReference = database.getReference("subjects")

    // 학기 번호와 과목 객체를 받아 Firebase에 과목을 추가하는 함수
    fun addSubject(semesterNumber: Int, subject: Subject) {
        val semesterReference = subjectsReference.child("semesters").child(semesterNumber.toString())
        val newSubjectKey = semesterReference.push().key
        newSubjectKey?.let {
            semesterReference.child(it).setValue(subject)
        }
    }

    // 특정 학기의 과목들을 가져오는 함수
    fun getSubjects(semesterNumber: Int, callback: (List<Subject>) -> Unit) {
        val semesterReference = subjectsReference.child("semesters").child(semesterNumber.toString())
        semesterReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val subjects = mutableListOf<Subject>()
                for (subjectSnapshot in dataSnapshot.children) {
                    val subject = subjectSnapshot.getValue(Subject::class.java)
                    subject?.let { subjects.add(it) }
                }
                callback(subjects)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                print("Error")
            }
        })
    }

    // 모든 학기의 과목들을 가져오는 함수
    fun getAllSubjects(callback: (List<Subject>) -> Unit) {
        val allSubjects = mutableListOf<Subject>()
        subjectsReference.child("semesters").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (semesterSnapshot in dataSnapshot.children) {
                    for (subjectSnapshot in semesterSnapshot.children) {
                        val subject = subjectSnapshot.getValue(Subject::class.java)
                        subject?.let { allSubjects.add(it) }
                    }
                }
                callback(allSubjects)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                print("Error")
            }
        })
    }
}
