package com.example.calendar.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context

import android.view.View

import android.view.LayoutInflater
import android.widget.TextView
import com.example.calendar.activities.MainActivity
import com.example.calendar.R
import com.example.calendar.interfaces.onDateClickListener
import kotlin.collections.ArrayList

class CalendarRecyclerViewAdapter(mainActivity: MainActivity, arrayList: ArrayList<ArrayList<String>>, onDateClickListener: onDateClickListener) : RecyclerView.Adapter<CalendarRecyclerViewAdapter.ViewHolder>() {

    var arrayList:ArrayList<ArrayList<String>> = arrayList
    var context: Context = mainActivity
    var onDateClickListener : onDateClickListener = onDateClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.calendar_row, parent, false)
        val holder = ViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var daysArrayList : ArrayList<String> = arrayList.get(position)

        holder.mondayTv.setText(daysArrayList.get(0))
        holder.tuesdayTv.setText(daysArrayList.get(1))
        holder.wednesdayTv.setText(daysArrayList.get(2))
        holder.thursdayTv.setText(daysArrayList.get(3))
        holder.fridayTv.setText(daysArrayList.get(4))
        holder.saturdayTv.setText(daysArrayList.get(5))
        holder.sundayTv.setText(daysArrayList.get(6))


        holder.mondayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(0) , position+1)
        })

        holder.tuesdayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(1) , position+1)
        })

        holder.wednesdayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(2) , position+1)
        })

        holder.thursdayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(3) , position+1)
        })

        holder.fridayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(4) , position+1)
        })

        holder.saturdayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(5) , position+1)
        })

        holder.sundayTv.setOnClickListener(View.OnClickListener {
            onDateClickListener.onDateClicked(daysArrayList.get(6) , position+1)
        })

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mondayTv : TextView
        val tuesdayTv : TextView
        val wednesdayTv : TextView
        val thursdayTv : TextView
        val fridayTv : TextView
        val saturdayTv : TextView
        val sundayTv : TextView

        init {

            mondayTv = itemView.findViewById(R.id.mondayTV)
            tuesdayTv = itemView.findViewById(R.id.tuesdayTV)
            wednesdayTv = itemView.findViewById(R.id.wednesdayTV)
            thursdayTv = itemView.findViewById(R.id.thursdayTV)
            fridayTv = itemView.findViewById(R.id.fridayTV)
            saturdayTv = itemView.findViewById(R.id.saturdayTV)
            sundayTv = itemView.findViewById(R.id.sundayTV)
        }
    }




}