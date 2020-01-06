package com.glarab.compass

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * TODO: document your custom view class.
 */
class CompassView : View {


    private var _circleColor: Int = Color.BLACK
    private var _lineColor: Int = Color.RED
    private var direction: Float = 0.toFloat()


    /**
     * The  color circle
     */
    var circleColor: Int
        get() = _circleColor
        set(value) {
            _circleColor = value
            invalidate()
        }

    /**
     * The  color line
     */
    var lineColor: Int
        get() = _lineColor
        set(value) {
            _lineColor = value
            invalidate()
        }


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.CompassView, defStyle, 0
        )


        _circleColor = a.getColor(
            R.styleable.CompassView_colorCircle,
            circleColor
        )

        _lineColor = a.getColor(
            R.styleable.CompassView_colorLine,
            lineColor
        )

        a.recycle()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            View.MeasureSpec.getSize(widthMeasureSpec),
            View.MeasureSpec.getSize(heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom


        val w = contentWidth
        val h = contentHeight
        val r: Int
        if (w > h) {
            r = h / 2
        } else {
            r = w / 2
        }

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 5f
        paint.color = circleColor

        canvas.drawCircle((w / 2).toFloat(), (h / 2).toFloat(), r.toFloat(), paint)
        paint.color = lineColor
        canvas.drawLine(
            (w / 2).toFloat(),
            (h / 2).toFloat(),
            (w / 2 + r * Math.sin((-direction).toDouble())).toFloat(),
            (h / 2 - r * Math.cos((-direction).toDouble())).toFloat(),
            paint
        )

    }

    fun update(dir: Float) {
        direction = dir
        invalidate()
    }
}
