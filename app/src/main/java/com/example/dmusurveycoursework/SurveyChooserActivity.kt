package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.dmusurveycoursework.Model.Student
import com.example.dmusurveycoursework.Model.SurveyDataBase
import java.text.SimpleDateFormat
import java.util.*

class SurveyChooserActivity : AppCompatActivity() {

    lateinit var simpleList : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_chooser)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getIntExtra("userID", 0)
        val mydatabase = SurveyDataBase(this)
        val surveyList = mydatabase.getAllSurveys()
        simpleList = findViewById<ListView>(R.id.SurveyListView)

        var surveyIDList = mutableListOf<Int>()
        var surveyNameArrayList = mutableListOf<String>()
        var startDateArrayList = mutableListOf<String>()
        var endDateArrayList = mutableListOf<String>()

        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        val nowDate = formatter.format(date)

        for(survey in surveyList){

            val endDate: Date = formatter.parse(survey.getEndDate())
            val today: Date = formatter.parse(nowDate)

            if (mydatabase.checkIfSurveyHasBeenAnswered(userId,survey.getSurveyID()) == 1) {
            }
            else if(today > endDate) {

            }
            else {
                surveyIDList.add(survey.getSurveyID())
                surveyNameArrayList.add(survey.getSurveyTitle())
                startDateArrayList.add(survey.getStartDate())
                endDateArrayList.add(survey.getEndDate())
            }
        }

        val surveyID: Array<Int> = surveyIDList.toTypedArray()
        val surveyName: Array<String> = surveyNameArrayList.toTypedArray()
        val startDates: Array<String> = startDateArrayList.toTypedArray()
        val endDate: Array<String> = endDateArrayList.toTypedArray()

        val customAdapter = CustomAdapter(applicationContext, surveyName, startDates, endDate)

        simpleList.isClickable = true
        simpleList!!.adapter = customAdapter
        simpleList.setOnItemClickListener {  parent, view, position, id ->
            val surveyID = surveyIDList[position]
            val surveyName = surveyNameArrayList[position]
            val startDates = startDateArrayList[position]
            val endDate = endDateArrayList[position]

            val i = Intent(this, AnswerSurveyActivity::class.java)
            i.putExtra("surveyID", surveyID)
            i.putExtra("userID", userId)
            startActivity(i)
        }
    }
}