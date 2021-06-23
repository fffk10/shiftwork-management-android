package com.example.shiftworkmanagement.model

import android.view.animation.Animation
import android.view.animation.Transformation

class AnimationForPieChartView(pieChartView: PieChartView): Animation() {
    private var pieChartView: PieChartView = pieChartView

    var rate: Int = 0

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)

        val thisRate: Float = rate * interpolatedTime
        pieChartView.rate = thisRate
        pieChartView.requestLayout()
    }
}