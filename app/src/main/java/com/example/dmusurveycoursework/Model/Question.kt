package com.example.dmusurveycoursework.Model

class Question(val questionId:Int, val surveyId:Int, val questionText:String) {

    override fun toString(): String {
        return "qID: $questionId, surveyId: $surveyId, questionTexT: $questionText"
    }
}