package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.dmusurveycoursework.Model.Admin
import com.example.dmusurveycoursework.Model.Student

class AdminWelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_welcome_page)

        val user = intent.getSerializableExtra("adminUser") as Admin

        val welcomeText = findViewById<TextView>(R.id.textViewAdminWelcome).apply {
           text = "Welcome back ${user.firstName}"
        }
    }

    fun createSurveyButton(view: View){
        val intent = Intent(this, SurveyCreatorActivity::class.java);
        startActivity(intent)
    }

    fun activeSurveysButton(view: View){
        val intent = Intent(this, ActiveSurveysActivity::class.java);
        startActivity(intent)
    }

    fun allSurveysButton(view: View){
        val intent = Intent(this, AllSurveysActivity::class.java);
        startActivity(intent)
    }

    fun logOutButton(view: View){
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
    }

    fun UpdateSurveysButton(view: View){
        val intent = Intent(this, UpdateSurveyChooserActivity::class.java);
        startActivity(intent)
    }
}