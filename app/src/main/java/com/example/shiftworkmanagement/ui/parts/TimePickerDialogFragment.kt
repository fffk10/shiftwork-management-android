package com.example.shiftworkmanagement.ui.parts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shiftworkmanagement.R

/**
 * Fragmentから呼び出されることを想定した汎用的な時間選択欄
 */
class TimePickerDialogFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_picker_dialog, container, false)
    }
}