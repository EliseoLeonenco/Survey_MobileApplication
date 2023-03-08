package com.example.dmusurveycoursework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.dmusurveycoursework.Model.SurveyDataBase

class SurveyStatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_stats)

        val surveyID = intent.getIntExtra("surveyID", 0)
        val surveyName = intent.getStringExtra("surveyName")
        val mydatabase = SurveyDataBase(this)
        val questionList = mydatabase.getAllQuestions(surveyID)
        val respondList = mydatabase.getSurveyResponsesByID(surveyID)

        findViewById<TextView>(R.id.textViewSurveyTitleStats).text = surveyName

        //Question texts
        findViewById<TextView>(R.id.textViewQuestion1Text).text = questionList[0].questionText
        findViewById<TextView>(R.id.textViewQuestion2Text).text = questionList[1].questionText
        findViewById<TextView>(R.id.textViewQuestion3Text).text = questionList[2].questionText
        findViewById<TextView>(R.id.textViewQuestion4Text).text = questionList[3].questionText
        findViewById<TextView>(R.id.textViewQuestion5Text).text = questionList[4].questionText
        findViewById<TextView>(R.id.textViewQuestion6Text).text = questionList[5].questionText
        findViewById<TextView>(R.id.textViewQuestion7Text).text = questionList[6].questionText
        findViewById<TextView>(R.id.textViewQuestion8Text).text = questionList[7].questionText
        findViewById<TextView>(R.id.textViewQuestion9Text).text = questionList[8].questionText
        findViewById<TextView>(R.id.textViewQuestion10Text).text = questionList[9].questionText

        var q1StronglyAgrCount:Int = 0
        var q1AgrCount:Int = 0
        var q1NeitherCount:Int = 0
        var q1DissCount:Int = 0
        var q1StronglyDissCount:Int = 0

        var q2StronglyAgrCount:Int = 0
        var q2AgrCount:Int = 0
        var q2NeitherCount:Int = 0
        var q2DissCount:Int = 0
        var q2StronglyDissCount:Int = 0

        var q3StronglyAgrCount:Int = 0
        var q3AgrCount:Int = 0
        var q3NeitherCount:Int = 0
        var q3DissCount:Int = 0
        var q3StronglyDissCount:Int = 0

        var q4StronglyAgrCount:Int = 0
        var q4AgrCount:Int = 0
        var q4NeitherCount:Int = 0
        var q4DissCount:Int = 0
        var q4StronglyDissCount:Int = 0

        var q5StronglyAgrCount:Int = 0
        var q5AgrCount:Int = 0
        var q5NeitherCount:Int = 0
        var q5DissCount:Int = 0
        var q5StronglyDissCount:Int = 0

        var q6StronglyAgrCount:Int = 0
        var q6AgrCount:Int = 0
        var q6NeitherCount:Int = 0
        var q6DissCount:Int = 0
        var q6StronglyDissCount:Int = 0

        var q7StronglyAgrCount:Int = 0
        var q7AgrCount:Int = 0
        var q7NeitherCount:Int = 0
        var q7DissCount:Int = 0
        var q7StronglyDissCount:Int = 0

        var q8StronglyAgrCount:Int = 0
        var q8AgrCount:Int = 0
        var q8NeitherCount:Int = 0
        var q8DissCount:Int = 0
        var q8StronglyDissCount:Int = 0

        var q9StronglyAgrCount:Int = 0
        var q9AgrCount:Int = 0
        var q9NeitherCount:Int = 0
        var q9DissCount:Int = 0
        var q9StronglyDissCount:Int = 0

        var q10StronglyAgrCount:Int = 0
        var q10AgrCount:Int = 0
        var q10NeitherCount:Int = 0
        var q10DissCount:Int = 0
        var q10StronglyDissCount:Int = 0

        for (response in respondList){
            when(response.id % 10) {
                1-> when(response.answerID){
                    1-> q1StronglyAgrCount++
                    2-> q1AgrCount++
                    3-> q1NeitherCount++
                    4-> q1DissCount++
                    5-> q1StronglyDissCount++
                }
                2-> when(response.answerID){
                    1-> q2StronglyAgrCount++
                    2-> q2AgrCount++
                    3-> q2NeitherCount++
                    4-> q2DissCount++
                    5-> q2StronglyDissCount++
                }
                3-> when(response.answerID){
                    1-> q3StronglyAgrCount++
                    2-> q3AgrCount++
                    3-> q3NeitherCount++
                    4-> q3DissCount++
                    5-> q3StronglyDissCount++
                }
                4-> when(response.answerID){
                    1-> q4StronglyAgrCount++
                    2-> q4AgrCount++
                    3-> q4NeitherCount++
                    4-> q4DissCount++
                    5-> q4StronglyDissCount++
                }
                5-> when(response.answerID){
                    1-> q5StronglyAgrCount++
                    2-> q5AgrCount++
                    3-> q5NeitherCount++
                    4-> q5DissCount++
                    5-> q5StronglyDissCount++
                }
                6-> when(response.answerID){
                    1-> q6StronglyAgrCount++
                    2-> q6AgrCount++
                    3-> q6NeitherCount++
                    4-> q6DissCount++
                    5-> q6StronglyDissCount++
                }
                7-> when(response.answerID){
                    1-> q7StronglyAgrCount++
                    2-> q7AgrCount++
                    3-> q7NeitherCount++
                    4-> q7DissCount++
                    5-> q7StronglyDissCount++
                }
                8-> when(response.answerID){
                    1-> q8StronglyAgrCount++
                    2-> q8AgrCount++
                    3-> q8NeitherCount++
                    4-> q8DissCount++
                    5-> q8StronglyDissCount++
                }
                9-> when(response.answerID){
                    1-> q9StronglyAgrCount++
                    2-> q9AgrCount++
                    3-> q9NeitherCount++
                    4-> q9DissCount++
                    5-> q9StronglyDissCount++
                }
                0-> when(response.answerID){
                    1-> q10StronglyAgrCount++
                    2-> q10AgrCount++
                    3-> q10NeitherCount++
                    4-> q10DissCount++
                    5-> q10StronglyDissCount++
                }
            }
        }

        findViewById<TextView>(R.id.textViewStronglyAgrCount1).text = q1StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount1).text = q1AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount1).text = q1NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount1).text = q1DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount1).text = q1StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount2).text = q2StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount2).text = q2AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount2).text = q2NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount2).text = q2DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount2).text = q2StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount3).text = q3StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount3).text = q3AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount3).text = q3NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount3).text = q3DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount3).text = q3StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount4).text = q4StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount4).text = q4AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount4).text = q4NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount4).text = q4DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount4).text = q4StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount5).text = q5StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount5).text = q5AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount5).text = q5NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount5).text = q5DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount5).text = q5StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount6).text = q6StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount6).text = q6AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount6).text = q6NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount6).text = q6DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount6).text = q6StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount7).text = q7StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount7).text = q7AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount7).text = q7NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount7).text = q7DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount7).text = q7StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount8).text = q8StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount8).text = q8AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount8).text = q8NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount8).text = q8DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount8).text = q8StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount9).text = q9StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount9).text = q9AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount9).text = q9NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount9).text = q9DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount9).text = q9StronglyDissCount.toString()

        findViewById<TextView>(R.id.textViewStronglyAgrCount10).text = q10StronglyAgrCount.toString()
        findViewById<TextView>(R.id.textViewAgrCount10).text = q10AgrCount.toString()
        findViewById<TextView>(R.id.textViewNeitherCount10).text = q10NeitherCount.toString()
        findViewById<TextView>(R.id.textViewStronglyDissCount10).text = q10DissCount.toString()
        findViewById<TextView>(R.id.textViewDissCount10).text = q10StronglyDissCount.toString()

    }
}