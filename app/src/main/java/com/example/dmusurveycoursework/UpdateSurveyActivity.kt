package com.example.dmusurveycoursework

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dmusurveycoursework.Model.SurveyDataBase
import java.util.*

class UpdateSurveyActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    var endDString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_survey)

        val surveyID = intent.getIntExtra("surveyID", 0)
        val surveyName = intent.getStringExtra("surveyName")
        val endDate = intent.getStringExtra("endDate")

        val mydatabase = SurveyDataBase(this)
        val questionList = mydatabase.getAllQuestions(surveyID)

        findViewById<TextView>(R.id.textViewUpdateSurveyTitle).text = surveyName

        findViewById<EditText>(R.id.editTextUpdateQuestion1).setText(questionList[0].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion2).setText(questionList[1].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion3).setText(questionList[2].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion4).setText(questionList[3].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion5).setText(questionList[4].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion6).setText(questionList[5].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion7).setText(questionList[6].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion8).setText(questionList[7].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion9).setText(questionList[8].questionText)
        findViewById<EditText>(R.id.editTextUpdateQuestion10).setText(questionList[9].questionText)

        val endDateBtn = findViewById<Button>(R.id.buttonUpdateEndDate)
        endDateBtn.text = endDate
        endDString = endDateBtn.text.toString()
    }

    fun updateEndDateButton(view: View) {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                endDString = "$dayOfMonth/${monthOfYear + 1}/$year"
                val buttonText = findViewById<Button>(R.id.buttonUpdateEndDate)
                buttonText.text = endDString
            }

        val datePicker = DatePickerDialog(
            this, dateSetListener, cal.get(Calendar.YEAR), cal.get(
                Calendar.MONTH
            ), cal.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()
    }

    fun updateSurveyButton(view: View) {
        val surveyID = intent.getIntExtra("surveyID", 0)
        val mydatabase = SurveyDataBase(this)
        val questionList = mydatabase.getAllQuestions(surveyID)

        val q1 = findViewById<EditText>(R.id.editTextUpdateQuestion1).text.toString()
        val q2 = findViewById<EditText>(R.id.editTextUpdateQuestion2).text.toString()
        val q3 = findViewById<EditText>(R.id.editTextUpdateQuestion3).text.toString()
        val q4 = findViewById<EditText>(R.id.editTextUpdateQuestion4).text.toString()
        val q5 = findViewById<EditText>(R.id.editTextUpdateQuestion5).text.toString()
        val q6 = findViewById<EditText>(R.id.editTextUpdateQuestion6).text.toString()
        val q7 = findViewById<EditText>(R.id.editTextUpdateQuestion7).text.toString()
        val q8 = findViewById<EditText>(R.id.editTextUpdateQuestion8).text.toString()
        val q9 = findViewById<EditText>(R.id.editTextUpdateQuestion9).text.toString()
        val q10 = findViewById<EditText>(R.id.editTextUpdateQuestion10).text.toString()

        if (q1.isEmpty() || q2.isEmpty() || q3.isEmpty() || q4.isEmpty() || q5.isEmpty() ||
            q6.isEmpty() || q7.isEmpty() || q8.isEmpty() || q9.isEmpty() || q10.isEmpty()) {
            Toast.makeText(this,"Please fill out all questions",Toast.LENGTH_LONG).show()
        } else {
            mydatabase.updateSurveyEndDateByID(surveyID, endDString)
            mydatabase.updateSurveyQuestionByID(questionList[0].questionId, q1)
            mydatabase.updateSurveyQuestionByID(questionList[1].questionId, q2)
            mydatabase.updateSurveyQuestionByID(questionList[2].questionId, q3)
            mydatabase.updateSurveyQuestionByID(questionList[3].questionId, q4)
            mydatabase.updateSurveyQuestionByID(questionList[4].questionId, q5)
            mydatabase.updateSurveyQuestionByID(questionList[5].questionId, q6)
            mydatabase.updateSurveyQuestionByID(questionList[6].questionId, q7)
            mydatabase.updateSurveyQuestionByID(questionList[7].questionId, q8)
            mydatabase.updateSurveyQuestionByID(questionList[8].questionId, q9)
            mydatabase.updateSurveyQuestionByID(questionList[9].questionId, q10)
        }
    }
}
