package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.dmusurveycoursework.Model.Admin
import com.example.dmusurveycoursework.Model.Student
import com.example.dmusurveycoursework.Model.SurveyDataBase

class AdminLogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_log_in)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    var currentAdminUser : Admin = Admin(0, "", "", "", "")

    fun adminLogIn(view: View){
        val userName = findViewById<EditText>(R.id.editTextAdminUsername).text.toString()
        val userPassword = findViewById<EditText>(R.id.editTextAdminPassword).text.toString()

        if(userName.isEmpty() || userPassword.isEmpty())
            Toast.makeText(this,"Please insert Username and Password", Toast.LENGTH_LONG).show()
        else {
            val myDataBase = SurveyDataBase(this)
            val result = myDataBase.getAdmin(
                Admin(-1," ", " ", userName, userPassword)
            )
            currentAdminUser = myDataBase.getAdminInfo(Admin(-1," ", " ", userName, userPassword))
            if( result == -1)
                Toast.makeText(this,"User Not Found, Please try again", Toast.LENGTH_LONG).show()
            else if( result == -2)
                Toast.makeText(this,"Error Cannot Open DataBase", Toast.LENGTH_LONG).show()
            else {
                Toast.makeText(this,"Log in successful", Toast.LENGTH_LONG).show()

                val intent = Intent(this, AdminWelcomePage::class.java).apply{
                    putExtra("adminUser", currentAdminUser)
                }
                startActivity(intent)
            }
        }
    }
}