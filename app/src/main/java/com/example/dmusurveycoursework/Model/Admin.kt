package com.example.dmusurveycoursework.Model

import java.io.Serializable

data class Admin(var id: Int, var firstName: String, var lastName: String, var userName: String, var password: String) :Serializable {
}