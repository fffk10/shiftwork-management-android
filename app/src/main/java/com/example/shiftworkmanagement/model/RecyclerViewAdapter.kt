package com.example.shiftworkmanagement.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shiftworkmanagement.R
import org.w3c.dom.Text
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

class RecyclerViewAdapter(requestMock: Array<Request?>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var requestMock: Array<Request?> = requestMock
    private val weekName = arrayOf(
        "日",
        "月",
        "火",
        "水",
        "木",
        "金",
        "土"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val requestId: TextView = view.findViewById(R.id.requestId)
        val requestWorkPlace: TextView = view.findViewById(R.id.workPlace)
        val requestDate: TextView = view.findViewById(R.id.requestDate)
        val requestTime: TextView = view.findViewById(R.id.requestTime)
        val requestStatus: TextView = view.findViewById(R.id.requestStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // シフト申請表示用
        val tempYear = requestMock[position]?.requestDate?.year.toString()
        val tempMonth = requestMock[position]?.requestDate?.monthValue.toString()
        val tempDay = requestMock[position]?.requestDate?.dayOfMonth.toString()
        var tempDayOfWeek = "曜日"

        when(requestMock[position]?.requestDate?.dayOfWeek) {
            DayOfWeek.SUNDAY -> tempDayOfWeek = weekName[0]
            DayOfWeek.MONDAY -> tempDayOfWeek = weekName[1]
            DayOfWeek.TUESDAY -> tempDayOfWeek = weekName[2]
            DayOfWeek.WEDNESDAY -> tempDayOfWeek = weekName[3]
            DayOfWeek.THURSDAY -> tempDayOfWeek = weekName[4]
            DayOfWeek.FRIDAY -> tempDayOfWeek = weekName[5]
            DayOfWeek.SATURDAY -> tempDayOfWeek = weekName[6]
        }

        // 時刻表示用
        val format = DateTimeFormatter.ofPattern("HH:mm")
        val tempStartTime = requestMock[position]?.startTime?.toLocalTime()?.format(format)
        val tempEndTime = requestMock[position]?.endTime?.toLocalTime()?.format(format)
        val tempRequestTime = "$tempStartTime ~ $tempEndTime"

        viewHolder.requestId.text = requestMock[position]?.id.toString()
        viewHolder.requestWorkPlace.text = requestMock[position]?.workPlace.toString()
        viewHolder.requestDate.text = "$tempYear/$tempMonth/$tempDay($tempDayOfWeek)"
        viewHolder.requestTime.text = tempRequestTime
        viewHolder.requestStatus.text = requestMock[position]?.status.toString()
    }

    override fun getItemCount(): Int {
        return requestMock.size
    }
}