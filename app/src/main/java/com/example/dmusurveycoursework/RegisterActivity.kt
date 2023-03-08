package com.example.dmusurveycoursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dmusurveycoursework.Model.Student
import com.example.dmusurveycoursework.Model.SurveyDataBase

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun registerStudentButton(view: View){
        val firstName = findViewById<EditText>(R.id.editTextNewStudentName).text.toString()
        val lastName  = findViewById<EditText>(R.id.editTextNewStudentLastName).text.toString()
        val userName  = findViewById<EditText>(R.id.editTextUserName).text.toString()
        val userPassword  = findViewById<EditText>(R.id.editTextStudentPassword).text.toString()

        if(firstName.isEmpty() || lastName.isEmpty() ) // First and last name are required
            Toast.makeText(this,"First name and last name are required!",Toast.LENGTH_LONG).show()
        else if(userName.isEmpty() || userPassword.isEmpty() ) // // User name and password are required
            Toast.makeText(this,"User name and Password are required!",Toast.LENGTH_LONG).show()
        else { // Save data
            // Create object
            val newUser = Student(-1, firstName, lastName, userName, userPassword)
            val mydatabase = SurveyDataBase(this)
            val result = mydatabase.addStudent(newUser)
            when (result) {
                1 -> Toast.makeText(this,"Your details has been add to the database successfully",Toast.LENGTH_LONG).show()
                -1 -> Toast.makeText(this,"Error on creating new account",Toast.LENGTH_LONG).show()
                -2 -> Toast.makeText(this,"Error can not open database",Toast.LENGTH_LONG).show()
                -3 -> Toast.makeText(this,"User name is already exist",Toast.LENGTH_LONG).show()
            }
        }
    }
}