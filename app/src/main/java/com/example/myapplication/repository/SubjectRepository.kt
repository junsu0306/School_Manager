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
        val semesterReference =
            subjectsReference.child("semesters").child(semesterNumber.toString())
        val newSubjectKey = semesterReference.push().key
        newSubjectKey?.let {
            semesterReference.child(it).setValue(subject)
        }
    }

    fun getSubjects(semesterNumber: Int, callback: (List<Subject>) -> Unit) {
        // Firebase Realtime Database에서 데이터 읽어오기
        val semesterReference =
            subjectsReference.child("semesters").child(semesterNumber.toString())
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

    fun getAllSubjects(callback: (List<Subject>) -> Unit) {
        val allSubjects = mutableListOf<Subject>()

        subjectsReference.child("semesters")
            .addListenerForSingleValueEvent(object : ValueEventListener {
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

