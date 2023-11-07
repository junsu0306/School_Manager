package com.example.myapplication

class Subject(val courseName: String, val grade: Double, val credits: Double)

class Semester(val semesterName: String, val subjects: List<Subject>)
