package com.example.shiftworkmanagement.ui.parts

import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.shiftworkmanagement.R
import java.lang.IllegalStateException
import java.util.*

/**
 * 開始時間と終了時間を入力するダイアログ
 */
class TimeSelectDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            // ダイアログ作成の元になるBuilder
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle("時間指定")
                .setView(layoutInflater.inflate(R.layout.fragment_time_select_dialog, null))
                .setPositiveButton(R.string.btn_ok,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                .setNegativeButton(R.string.btn_cancel,
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })

            return builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
