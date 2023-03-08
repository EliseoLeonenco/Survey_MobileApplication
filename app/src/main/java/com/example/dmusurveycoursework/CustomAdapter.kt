package com.example.dmusurveycoursework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private val appContext: Context, private val surveyNameList: Array<String>,
                    private val startDateList: Array<String>, private val endDateList: Array<String>
) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return surveyNameList.size
    }

    override fun getItem(i: Int): Any? {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view
        view = inflater.inflate(R.layout.activity_list_view, parent, false)

        val surveyName = view.findViewById<TextView>(R.id.textViewSurveyName)
        val startDate = view.findViewById<TextView>(R.id.textViewStartDate)
        val endDate = view.findViewById<TextView>(R.id.textViewEndDate)
        surveyName.text = surveyNameList[position]
        startDate.text = startDateList[position]
        endDate.text = endDateList[position]
        return view
    }
}