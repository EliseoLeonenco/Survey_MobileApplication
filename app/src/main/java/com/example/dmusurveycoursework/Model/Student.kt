package com.example.dmusurveycoursework.Model

import java.io.Serializable

data class Student(var id: Int, var firstName: String, var lastName: String, var userName: String, var password: String) : Serializable{

    override fun toString(): String {
        return "Student: id: $id, fname: $firstName, lastn: $lastName, uname: $userName, pass: $password"
    }
}

