package com.example.myapplication

class Subject(val courseName: String, val grade: Double, val credits: Double,val major:Double)

class Semester(val semesterName: String, val subjects: List<Subject>)
