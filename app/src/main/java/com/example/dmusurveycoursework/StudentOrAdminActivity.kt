package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StudentOrAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_or_admin)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun userIsAdminButton(view: View){
        val intent = Intent(this, AdminLogInActivity::class.java);
        startActivity(intent);
    }

    fun userIsStudentButton(view: View){
        val intent = Intent(this, LogInActivity::class.java);
        startActivity(intent);
    }
}