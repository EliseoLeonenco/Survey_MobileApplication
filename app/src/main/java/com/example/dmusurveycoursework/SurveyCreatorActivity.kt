package com.example.dmusurveycoursework

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.dmusurveycoursework.Model.SurveyDataBase
import java.text.SimpleDateFormat
import java.util.*

class SurveyCreatorActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_creator)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    var cal = Calendar.getInstance()
    var endDString:String = ""

    fun chooseEndDate(view: View){
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            endDString = "$dayOfMonth/${monthOfYear+1}/$year"
            val buttonText = findViewById<Button>(R.id.selectDateButton)
            buttonText.text = endDString
        }

        val datePicker = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()
    }
    fun createSurveyButton(view: View) {

        var surveyTitle = findViewById<EditText>(R.id.editTextSurveyTitle).text.toString()
        var q1 = findViewById<EditText>(R.id.editTextQuestion1).text.toString()
        var q2 = findViewById<EditText>(R.id.editTextQuestion2).text.toString()
        var q3 = findViewById<EditText>(R.id.editTextQuestion3).text.toString()
        var q4 = findViewById<EditText>(R.id.editTextQuestion4).text.toString()
        var q5 = findViewById<EditText>(R.id.editTextQuestion5).text.toString()
        var q6 = findViewById<EditText>(R.id.editTextQuestion6).text.toString()
        var q7 = findViewById<EditText>(R.id.editTextQuestion7).text.toString()
        var q8 = findViewById<EditText>(R.id.editTextQuestion8).text.toString()
        var q9 = findViewById<EditText>(R.id.editTextQuestion9).text.toString()
        var q10 = findViewById<EditText>(R.id.editTextQuestion10).text.toString()

        //Get current date to set as startDate
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        val startDate = formatter.format(date)

        //surveyID for other tables
        val myDataBase = SurveyDataBase(this)
        val surveyID = myDataBase.getLastSurveyID() + 1

        if (surveyTitle.isEmpty()){
            Toast.makeText(this, "Please insert a Survey Title", Toast.LENGTH_LONG).show()

        } else if(endDString.isEmpty()) {
            Toast.makeText(this, "Please insert an End Date", Toast.LENGTH_LONG).show()

        } else if (q1.isEmpty() || q2.isEmpty() || q3.isEmpty() || q4.isEmpty() || q5.isEmpty() ||
                q6.isEmpty() || q7.isEmpty() || q8.isEmpty() || q9.isEmpty() || q10.isEmpty()){
            Toast.makeText(this, "Please insert all Survey Questions", Toast.LENGTH_LONG).show()
        } else {
            val mydatabase = SurveyDataBase(this)
            val result = mydatabase.addSurveyTitle(surveyTitle)

            when (result) {
                1 -> {
                    Toast.makeText(this,"Survey has been added to the database successfully",Toast.LENGTH_LONG).show()
                    mydatabase.addSurveyDates(surveyID, startDate, endDString)
                    mydatabase.addSurveyQuestions(q1,surveyID)
                    mydatabase.addSurveyQuestions(q2,surveyID)
                    mydatabase.addSurveyQuestions(q3,surveyID)
                    mydatabase.addSurveyQuestions(q4,surveyID)
                    mydatabase.addSurveyQuestions(q5,surveyID)
                    mydatabase.addSurveyQuestions(q6,surveyID)
                    mydatabase.addSurveyQuestions(q7,surveyID)
                    mydatabase.addSurveyQuestions(q8,surveyID)
                    mydatabase.addSurveyQuestions(q9,surveyID)
                    mydatabase.addSurveyQuestions(q10,surveyID)
                    val intent = Intent(this, AdminWelcomePage::class.java);
                    startActivity(intent)
                }
                -1 -> Toast.makeText(this,"Error on creating new survey",Toast.LENGTH_LONG).show()
                -2 -> Toast.makeText(this,"Error can not open database",Toast.LENGTH_LONG).show()
                -3 -> Toast.makeText(this,"Survey Title already exists",Toast.LENGTH_LONG).show()
            }
        }
    }
}
