package com.example.shiftworkmanagement.ui.parts

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.style.BulletSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.shiftworkmanagement.R

/**
 * Fragmentから呼び出すことを想定した汎用的な日付選択欄
 */
class DatePickerDialogFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    interface DatePickerDialogListener {
        fun onDateSet(year: Int, month: Int, dayOfMonth: Int)
    }

    companion object {
        private const val ARG_YEAR = "ARG_YEAR"
        private const val ARG_MONTH = "ARG_MONTH"
        private const val ARG_DAY_OF_MONTH = "ARG_DAY_OF_MONTH"

        @JvmStatic
        fun newInstance(year: Int, month: Int, dayOfMonth: Int): DatePickerDialogFragment {
            return DatePickerDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_YEAR, year)
                    putInt(ARG_MONTH, month)
                    putInt(ARG_DAY_OF_MONTH, dayOfMonth)
                }
            }
        }
        @JvmStatic
        fun newInstance(): DatePickerDialogFragment {
            return DatePickerDialogFragment()
        }
    }

    private lateinit var listener: DatePickerDialogListener

    /**
     * Fragmentから呼び出されることを想定している
     * Activityから呼び出される場合は修正が必要
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (parentFragment) {
            is DatePickerDialogListener -> listener = parentFragment as DatePickerDialogListener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_date_picker_dialog, container, false)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        TODO("Not yet implemented")
    }

}