package com.example.shiftworkmanagement.ui.home

import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shiftworkmanagement.databinding.FragmentHomeBinding
import com.example.shiftworkmanagement.model.PieChartView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    /**
     * 円グラフ表示用
     */
    lateinit var editRate: EditText
    lateinit var textRate: TextView
    lateinit var buttonRate: Button
    lateinit var checkNotClockWise: CheckBox
    lateinit var pieChartView: PieChartView
    lateinit var relativeLayout: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = requireContext()
        relativeLayout = RelativeLayout(context)

        editRate = EditText(context)
        editRate.setText("75")
        editRate.inputType = InputType.TYPE_CLASS_NUMBER

        textRate = TextView(context)
        textRate.gravity = Gravity.CENTER
        textRate.text = "%"

        buttonRate = Button(context)
        buttonRate.text = "グラフ表示"
        buttonRate.setOnClickListener {
            drawChart()
        }

        checkNotClockWise = CheckBox(context)
        checkNotClockWise.text = "反時計回りにする"

        pieChartView = PieChartView(context)

        return pieChartView
    }

    private fun changeScreen() {
        val widthValue = relativeLayout.width
        val heightValue = relativeLayout.height

        val buttonHeight = heightValue / 10
        val spaceWidth = (widthValue - buttonHeight * 6) / 2
        relativeLayout.addView(editRate, getLayoutParam(spaceWidth, 50, buttonHeight, buttonHeight))
        relativeLayout.addView(
            textRate,
            getLayoutParam(spaceWidth + buttonHeight, 50, buttonHeight, buttonHeight)
        )
        relativeLayout.addView(
            checkNotClockWise,
            getLayoutParam(spaceWidth + buttonHeight * 2, 50, buttonHeight * 2, buttonHeight)
        )
        relativeLayout.addView(
            buttonRate,
            getLayoutParam(spaceWidth + buttonHeight * 4, 50, buttonHeight * 2, buttonHeight)
        )

        var chartWidth: Int = (widthValue * 0.8).toInt()
        if (widthValue > heightValue) {
            chartWidth = (heightValue * 0.8).toInt()
        }

        relativeLayout.addView(
            pieChartView,
            getLayoutParam(
                widthValue / 2 - chartWidth / 2,
                50 + buttonHeight,
                chartWidth,
                chartWidth
            )
        )
    }

    private fun getLayoutParam(
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ): RelativeLayout.LayoutParams {
        val param1 = RelativeLayout.LayoutParams(width, height)
        param1.leftMargin = x
        param1.topMargin = y
        param1.addRule(RelativeLayout.ALIGN_TOP)
        return param1
    }

    private fun drawChart() {
        pieChartView.isNotClockWise = checkNotClockWise.isChecked
        val rateString: String = editRate.text.toString()
        val rate: Int = Integer.parseInt(rateString)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}