package com.taweewong.textview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatTextView

class OuterStrokeTextView  @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

	@ColorInt
	var outlineColor = 0
	@Dimension(unit = Dimension.DP)
	var outlineWidth = 0f

	private var isDrawing = false

	init {
		with(context.obtainStyledAttributes(attrs, R.styleable.OuterStrokeTextView)) {
			try {
				if (attrs != null) {
					outlineColor = getColor(R.styleable.OuterStrokeTextView_outlineColor, Color.TRANSPARENT)
					outlineWidth = getDimension(R.styleable.OuterStrokeTextView_outlineWidth, 0f)
				} else {
					outlineColor = currentTextColor
					outlineWidth = 0f
				}
			} finally {
				recycle()
			}
		}
	}

	override fun invalidate() {
		if (isDrawing) return
		super.invalidate()
	}

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec)
		if (measuredWidth > 0 && measuredHeight > 0) {
			val newWidth = (measuredWidth + outlineWidth).toInt()
			val newHeight = (measuredHeight + (outlineWidth)).toInt()
			setMeasuredDimension(
				MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY),
				MeasureSpec.makeMeasureSpec(newHeight, MeasureSpec.EXACTLY)
			)
		}
	}

	override fun onDraw(canvas: Canvas?) {
		canvas?.let {
			if (outlineWidth != 0f && outlineColor != 0) {
				val originalTextColor = currentTextColor
				val width = measuredWidth / 2f
				val height = (measuredHeight / 2f) - ((paint.descent() + paint.ascent()) / 2)

				isDrawing = true
				paint.textAlign = Paint.Align.CENTER

				//Draw outline
				paint.style = Paint.Style.STROKE
				paint.strokeWidth = outlineWidth
				paint.color = outlineColor
				canvas.drawText(text.toString(), width, height, paint)

				//Draw text
				paint.style = Paint.Style.FILL
				paint.color = originalTextColor
				canvas.drawText(text.toString(), width, height, paint)

				isDrawing = false
			} else {
				super.onDraw(canvas)
			}
		}
	}
}