package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.example.myapplication.Subject
import com.google.firebase.database.ktx.database


class SubjectRepository {
    private val database: FirebaseDatabase = Firebase.database
    private val subjectsReference: DatabaseReference = database.getReference("subjects")

    fun addSubject(semesterNumber: Int, subject: Subject) {
        // Firebase Realtime Database에 데이터 추가
        subjectsReference.child("semesters").child(semesterNumber.toString()).push()
            .setValue(subject)
    }

    fun getSubjects(semesterNumber: Int, callback: (List<Subject>) -> Unit) {
        // Firebase Realtime Database에서 데이터 읽어오기
        subjectsReference.child("semesters").child(semesterNumber.toString())
            .addListenerForSingleValueEvent(object : ValueEventListener {
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



}