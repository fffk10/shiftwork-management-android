package com.example.shiftworkmanagement.data.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

class PieChartView : View {
    var rate: Float = 0f
    var isNotClockWise: Boolean = false

    constructor(context: Context) : super(context, null)

    /**
     * 描画処理
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBaseChart(canvas)
        drawValueChart(canvas)
    }

    /**
     * 軸の円の表示
     */
    private fun drawBaseChart(canvas: Canvas?) {
        /**
         * 描画設定
         */
        val paint = Paint()
        val strokeWidth: Float = (width / 8).toFloat()

        // 円のデザインをセット
        paint.color = Color.rgb(255, 212, 121)
        paint.strokeWidth = strokeWidth
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND

        val rect = RectF(
            strokeWidth / 2,
            strokeWidth / 2,
            width.toFloat() - strokeWidth / 2,
            height.toFloat() - strokeWidth / 2
        )
        canvas?.drawArc(rect, 0f, 360f, false, paint)
    }

    /**
     * 値を表示する円の表示
     */
    private fun drawValueChart(canvas: Canvas?) {
        /**
         * 描画設定
         */
        val paint = Paint()
        val strokeWidth:Float = (width / 8).toFloat()

        // 円のデザインをセット
        paint.color = Color.rgb(255,138,216)
        paint.strokeWidth = strokeWidth
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND

        val rect = RectF(
            strokeWidth / 2,
            strokeWidth / 2,
            width.toFloat() - strokeWidth / 2,
            height.toFloat() - strokeWidth / 2
        )
        var angle = rate / 100 * 360
        if (isNotClockWise) {
            angle *= -1
        }
        canvas?.drawArc(rect, -90f, angle, false, paint)
    }
}