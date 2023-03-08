package com.example.dmusurveycoursework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.dmusurveycoursework.Model.Student
import com.example.dmusurveycoursework.Model.SurveyDataBase

class AnswerSurveyActivity : AppCompatActivity() {

    var questionCounter:Int = 0
    var answerCounter:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_survey)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val surveyID = intent.getIntExtra("surveyID", 0)
        val txq = findViewById<TextView>(R.id.textViewQuestion)

        val mydatabase = SurveyDataBase(this)
        val questionList = mydatabase.getAllQuestions(surveyID)

        txq.text = questionList[questionCounter].questionText.toString()

    }

    var answerList = mutableListOf<Int>(0,0,0,0,0,0,0,0,0,0)

    fun nextQuestionButton(view: View) {
        val surveyID = intent.getIntExtra("surveyID", 0)
        val userId = intent.getIntExtra("userID", 0)

        val mydatabase = SurveyDataBase(this)
        val questionList = mydatabase.getAllQuestions(surveyID)
        val txtQuestion = findViewById<TextView>(R.id.textViewQuestion)

        val radioGrp = findViewById<RadioGroup>(R.id.radioGrpAnswers)
        val radioID = radioGrp.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(radioID)

        if(radioID == -1){
            Toast.makeText(this,"Please answer the question",Toast.LENGTH_LONG).show()
        } else {
            when(radioButton.text){
                "Strongly Agree" -> {
                    answerList[answerCounter] = 1
                    answerCounter++
                }
                "Agree" -> {
                    answerList[answerCounter] = 2
                    answerCounter++
                }
                "Neither Agree nor Disagree" -> {
                    answerList[answerCounter] = 3
                    answerCounter++
                }
                "Disagree" -> {
                    answerList[answerCounter] = 4
                    answerCounter++
                }
                "Strongly Disagree" -> {
                    answerList[answerCounter] = 5
                    answerCounter++
                }
            }
            if(questionCounter<9){
                questionCounter++
                txtQuestion.text = questionList[questionCounter].questionText.toString()
            } else {
                val mydatabase = SurveyDataBase(this)
                val q1 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[0].questionId,answerList[0])
                val q2 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[1].questionId,answerList[1])
                val q3 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[2].questionId,answerList[2])
                val q4 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[3].questionId,answerList[3])
                val q5 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[4].questionId,answerList[4])
                val q6 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[5].questionId,answerList[5])
                val q7 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[6].questionId,answerList[6])
                val q8 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[7].questionId,answerList[7])
                val q9 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[8].questionId,answerList[8])
                val q10 = mydatabase.addAnswerToDatabase(userId,surveyID,questionList[9].questionId,answerList[9])

                Toast.makeText(this,"Survey has been Completed",Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java);
                startActivity(intent)
            }
        }
    }

    fun previousQuestionButton(view: View) {
        val surveyID = intent.getIntExtra("surveyID", 0)

        val mydatabase = SurveyDataBase(this)
        val questionList = mydatabase.getAllQuestions(surveyID)
        val txtQuestion = findViewById<TextView>(R.id.textViewQuestion)

        if(questionCounter>0){
            questionCounter--
            answerCounter--
            txtQuestion.text = questionList[questionCounter].questionText.toString()
        }
    }
}