package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.dmusurveycoursework.Model.SurveyDataBase

class UpdateSurveyChooserActivity : AppCompatActivity() {

    lateinit var simpleList : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_survey_chooser)

        val mydatabase = SurveyDataBase(this)
        val surveyList = mydatabase.getAllSurveys()
        simpleList = findViewById<ListView>(R.id.UpdateSurveyListView)

        var surveyIDList = mutableListOf<Int>()
        var surveyNameArrayList = mutableListOf<String>()
        var startDateArrayList = mutableListOf<String>()
        var endDateArrayList = mutableListOf<String>()

        for(survey in surveyList){
            surveyIDList.add(survey.getSurveyID())
            surveyNameArrayList.add(survey.getSurveyTitle())
            startDateArrayList.add(survey.getStartDate())
            endDateArrayList.add(survey.getEndDate())
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

            val i = Intent(this, UpdateSurveyActivity::class.java)
            i.putExtra("surveyID", surveyID)
            i.putExtra("surveyName", surveyName)
            i.putExtra("endDate", endDate)
            startActivity(i)
        }

    }
}