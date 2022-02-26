package com.example.calendar.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.YearMonth
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendar.R
import com.example.calendar.adapter.CalendarRecyclerViewAdapter
import com.example.calendar.interfaces.onDateClickListener
import java.util.Locale

import java.time.temporal.WeekFields

class MainActivity : AppCompatActivity() , onDateClickListener {

    lateinit var myTextView : TextView
    lateinit var calendarRecyclerView : RecyclerView
    lateinit var currentDate : LocalDate
    lateinit var leftButton : ImageView
    lateinit var rightButton : ImageView
    lateinit var sdTextView : TextView
    lateinit var wnTextView: TextView
    var selectedDate : Int = 0
    var selectedMonth : String = ""
    var selectedYear : String = ""
    var selectedWeek : Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseViews()

        currentDate = LocalDate.now()
        selectedDate = currentDate.dayOfMonth
        selectedMonth = currentDate.month.toString()
        selectedYear = currentDate.year.toString()
        val weekFields = WeekFields.of(Locale.getDefault())
        val weekNumber: Int = currentDate.get(weekFields.weekOfMonth())
        setTexts(selectedDate , selectedMonth , selectedYear , weekNumber)

        setUpCalendar(currentDate)

        leftButton.setOnClickListener(View.OnClickListener {
            currentDate = currentDate.minusMonths(1)
            setUpCalendar(currentDate)
        })

        rightButton.setOnClickListener(View.OnClickListener {
            currentDate = currentDate.plusMonths(1)
            setUpCalendar(currentDate)
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpCalendar(currentLocalDate: LocalDate) {

        var arrayList: ArrayList<String> = ArrayList()
        var finalArrayList: ArrayList<ArrayList<String>> = ArrayList()
        var currentDate = currentLocalDate.dayOfMonth;
        var currentMonth = currentLocalDate.month
        var currentYear = currentLocalDate.year
        var lengthOfMonth = YearMonth.of(currentYear , currentMonth).lengthOfMonth()
        var firstOfMonth = currentLocalDate.withDayOfMonth(1)
        var dayNumberOfFirst = firstOfMonth.dayOfWeek.value
        var currentDayOfWeek = currentLocalDate.dayOfWeek.value

        myTextView.text = currentMonth.toString() + " " + currentYear.toString()
        selectedMonth = currentMonth.toString()
        selectedYear = currentYear.toString()

        var dayOfWeek = 1

        while (dayOfWeek<dayNumberOfFirst){
            arrayList.add("")
            dayOfWeek++
        }

        var date = 1

        while (date <= lengthOfMonth){

            arrayList.add(date.toString())
            date++
            dayOfWeek++

            if(dayOfWeek==8){
                dayOfWeek=1
                finalArrayList.add(arrayList)
                arrayList = ArrayList()

            }
        }

        while (dayOfWeek<8){
            arrayList.add("")
            dayOfWeek++
        }

        finalArrayList.add(arrayList)

        val adapter = CalendarRecyclerViewAdapter(this, finalArrayList , this)
        val linearLayoutManager = LinearLayoutManager(this)
        calendarRecyclerView.setLayoutManager(linearLayoutManager)
        calendarRecyclerView.setAdapter(adapter)

    }

    private fun initialiseViews() {
        myTextView = findViewById(R.id.mYTextView)
        calendarRecyclerView = findViewById(R.id.recyclerView)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        sdTextView = findViewById(R.id.sdTextView)
        wnTextView = findViewById(R.id.wnTextView)

    }

    private fun setTexts(selectedDate: Int, selectedMonth: String, selectedYear: String, weekNumber: Int) {
        sdTextView.setText("Selected date: " + selectedDate + " " + selectedMonth + " " + selectedYear)
        wnTextView.setText("Week number (of this month): " + weekNumber)
    }

    override fun onDateClicked(date: String, weekNumber: Int) {
        super.onDateClicked(date, weekNumber)

        sdTextView.setText("Selected date: " + date + " " + selectedMonth + " " + selectedYear )
        wnTextView.setText("Week number (of this month): " + weekNumber)

    }

}