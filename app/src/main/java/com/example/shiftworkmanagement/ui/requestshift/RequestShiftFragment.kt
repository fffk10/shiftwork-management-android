package com.example.shiftworkmanagement.ui.requestshift

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shiftworkmanagement.databinding.FragmentRequestShiftBinding
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RequestShiftFragment : Fragment() {

    private lateinit var requestShiftViewModel: RequestShiftViewModel
    private var _binding: FragmentRequestShiftBinding? = null
    private val binding get() = _binding!!
    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null
    private var startHour: Int? = null
    private var startMinute: Int? = null
    private var endHour: Int? = null
    private var endMinute: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestShiftViewModel = ViewModelProvider(this).get(RequestShiftViewModel::class.java)

        _binding = FragmentRequestShiftBinding.inflate(inflater, container, false)

        // 現在時刻を取得
        val now = LocalDateTime.now()


        binding.selectDateText.text = "${now.year}/${now.monthValue}/${now.dayOfMonth}"

        val timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss")

        binding.startTime.text = "${now.toLocalTime().format(timeFormat)}"
        binding.endTime.text = "${now.toLocalTime().format(timeFormat)}"

        // カレンダーの日付選択をシフト申請日に
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            this.year = year
            this.month = month
            this.day = day

            binding.startTime.setTextColor(Color.WHITE)
            binding.startTimeTitle.setTextColor(Color.WHITE)
            binding.endTime.setTextColor(Color.WHITE)
            binding.endTimeTitle.setTextColor(Color.WHITE)

            binding.startTimeTitle.setOnClickListener {
                showTimePickerDialog(binding.root, true)
            }
            binding.startTime.setOnClickListener {
                showTimePickerDialog(binding.root, true)
            }

            binding.endTimeTitle.setOnClickListener {
                showTimePickerDialog(binding.root, false)
            }
            binding.endTime.setOnClickListener {
                showTimePickerDialog(binding.root, false)
            }
            binding.selectDateText.text = "$year/${month + 1}/$day"
        }

        return binding.root
    }

    // 開始・終了時刻選択
    private fun showTimePickerDialog(view: View, start: Boolean) {

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            if (start) {
                startHour = hour
                startMinute = minute
                binding.startTime.text = "$startHour 時 $startMinute 分"
            } else {
                endHour = hour
                endMinute = minute
                binding.endTime.text = "$endHour 時 $endMinute 分"
            }
        }

        TimePickerDialog(
            requireContext(),
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}