package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.dmusurveycoursework.Model.Student

class WelcomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val user = intent.getSerializableExtra("user") as Student

        val welcomeText = findViewById<TextView>(R.id.textViewWelcome).apply {
            text = "Welcome back ${user.firstName}"
        }
    }

    fun logOutButton(view: View){
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent)
    }

    fun surveysButton(view: View){
        val user = intent.getSerializableExtra("user") as Student

        val intent = Intent(this, SurveyChooserActivity::class.java).apply{
            putExtra("userID", user.id)
        }
        startActivity(intent)
    }




}