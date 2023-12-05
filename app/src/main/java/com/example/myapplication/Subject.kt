package com.example.myapplication

class Subject() {

    var courseName: String = ""
    var grade: Double = 0.0
    var credits: Int = 0
    var major: Int = 0



    constructor(courseName: String, grade: Double, credits: Int, major: Int) : this() {
        this.courseName = courseName
        this.grade = grade
        this.credits = credits
        this.major = major
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val subject = other as Subject

        if (courseName != subject.courseName) return false
        if (grade != subject.grade) return false
        if (credits != subject.credits) return false
        if (major != subject.major) return false

        return true
    }

    override fun hashCode(): Int {
        var result = courseName.hashCode()
        result = 31 * result + grade.hashCode()
        result = 31 * result + credits.hashCode()
        result = 31 * result + major.hashCode()
        return result
    }
}

class Semester(val semesterName: String, val subjects: List<Subject>)

data class SemesterInfo(val semesterNumber: Int, val subjects: List<Subject>)
