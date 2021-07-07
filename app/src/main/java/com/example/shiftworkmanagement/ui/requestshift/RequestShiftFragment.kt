package com.example.shiftworkmanagement.ui.requestshift

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.shiftworkmanagement.R
import com.example.shiftworkmanagement.databinding.FragmentRequestShiftBinding
import com.example.shiftworkmanagement.ui.parts.TimeSelectDialogFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * シフト申請用画面
 */
class RequestShiftFragment : Fragment() {

    private lateinit var requestShiftViewModel: RequestShiftViewModel
    private var _binding: FragmentRequestShiftBinding? = null
    private val binding get() = _binding!!

    /**
     * 申請情報のプロパティ
     */
    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null
    private var startHour: Int? = null
    private var startMinute: Int? = null
    private var endHour: Int? = null
    private var endMinute: Int? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requestShiftViewModel = ViewModelProvider(this).get(RequestShiftViewModel::class.java)

        _binding = FragmentRequestShiftBinding.inflate(inflater, container, false)

        // 現在時刻を取得
        val now = LocalDateTime.now()
//        binding.selectDateText.text = "${now.year}/${now.monthValue}/${now.dayOfMonth}"

        val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

//        binding.startTime.text = "${now.toLocalTime().format(timeFormat)}"
//        binding.endTime.text = "${now.toLocalTime().format(timeFormat)}"

        // カレンダーの日付選択をシフト申請日に
        binding.calendarView.setOnDayClickListener {
            OnDayClickListener { eventDay ->
                val nowCalendar = eventDay.calendar
                this.year = nowCalendar.get(Calendar.YEAR)
                this.month = nowCalendar.get(Calendar.MONTH) + 1
                this.day = nowCalendar.get(Calendar.DATE)
            }

            TimeSelectDialogFragment().show(childFragmentManager, TimeSelectDialogFragment::class.simpleName)

//            binding.startTime.setTextColor(R.color.jet)
//            binding.startTimeTitle.setTextColor(R.color.jet)
//            binding.endTime.setTextColor(R.color.jet)
//            binding.endTimeTitle.setTextColor(R.color.jet)

//            binding.startTimeTitle.setOnClickListener {
//                showTimePickerDialog(binding.root, true)
//            }
//            binding.startTime.setOnClickListener {
//                showTimePickerDialog(binding.root, true)
//            }
//
//            binding.endTimeTitle.setOnClickListener {
//                showTimePickerDialog(binding.root, false)
//            }
//            binding.endTime.setOnClickListener {
//                showTimePickerDialog(binding.root, false)
//            }
//            binding.selectDateText.text = "$year/${month?.plus(1)}/$day"

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
//                binding.startTime.text = "$startHour:$startMinute"
            } else {
                endHour = hour
                endMinute = minute
//                binding.endTime.text = "$endHour:$endMinute"
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