package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.dmusurveycoursework.Model.SurveyDataBase
import java.text.SimpleDateFormat
import java.util.*

class AllSurveysActivity : AppCompatActivity() {

    lateinit var simpleList : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_surveys)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val mydatabase = SurveyDataBase(this)
        val surveyList = mydatabase.getAllSurveys()
        simpleList = findViewById<ListView>(R.id.AdminSurveyListView)

        var surveyIDList = mutableListOf<Int>()
        var surveyNameArrayList = mutableListOf<String>()
        var startDateArrayList = mutableListOf<String>()
        var endDateArrayList = mutableListOf<String>()
        var participantsArrayList = mutableListOf<String>()

        for(survey in surveyList){
                surveyIDList.add(survey.getSurveyID())
                surveyNameArrayList.add(survey.getSurveyTitle())
                startDateArrayList.add(survey.getStartDate())
                endDateArrayList.add(survey.getEndDate())
        }

        for (id in surveyIDList) {
            val count = mydatabase.getSurveyRespondCountByID(id)
            participantsArrayList.add(count.toString())
        }

        val surveyName: Array<String> = surveyNameArrayList.toTypedArray()
        val startDates: Array<String> = startDateArrayList.toTypedArray()
        val endDate: Array<String> = endDateArrayList.toTypedArray()
        val participants: Array<String> = participantsArrayList.toTypedArray()

        val adminCustomAdapter = AdminCustomAdapter(applicationContext, surveyName, startDates, endDate, participants)

        simpleList.isClickable = true
        simpleList!!.adapter = adminCustomAdapter

        simpleList.setOnItemClickListener {  parent, view, position, id ->
            val surveyID = surveyIDList[position]
            val surveyName = surveyNameArrayList[position]
            val startDates = startDateArrayList[position]
            val endDate = endDateArrayList[position]
            val participants = participantsArrayList[position]

            val i = Intent(this, SurveyStatsActivity::class.java)
            i.putExtra("surveyID", surveyID)
            i.putExtra("surveyName", surveyName)
            startActivity(i)
        }
    }
}