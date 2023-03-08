package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.graphics.toColor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun logInButton(view: View) {

        val intent = Intent(this, StudentOrAdminActivity::class.java);
        startActivity(intent)
    }

    fun registerButton(view: View) {
        val intent = Intent(this, RegisterActivity::class.java);
        startActivity(intent);
    }
}