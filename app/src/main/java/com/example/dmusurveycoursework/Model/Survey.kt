package com.example.dmusurveycoursework.Model

class Survey(private var surveyId:Int, private var surveyTitle:String, private var startDate:String, private var endDate:String, private var questionsList: ArrayList<Question>?) {

    constructor(surveyId:Int, surveyTitle: String, startDate:String, endDate:String) : this(surveyId,surveyTitle,startDate,endDate,null) {
        this.surveyId=surveyId
        this.surveyTitle=surveyTitle
        this.startDate=startDate
        this.endDate=endDate
    }

    override fun toString(): String {
        return "surveyId: $surveyId, surveyTitle: $surveyTitle, startDate: $startDate"
    }

    fun getSurveyID():Int{
        return surveyId
    }

    fun getSurveyTitle():String{
        return surveyTitle
    }

    fun getStartDate():String{
        return startDate
    }

    fun getEndDate():String{
        return endDate
    }
}