@file:Suppress("DEPRECATION")

package com.example.snowflakes2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.AsyncTask
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

data class Snowflake(
    var x: Float,
    var y: Float,
    var velocity: Float,
    val radius: Float,
    val color: Int
)

lateinit var snow: Array<Snowflake>
val paint = Paint()
var h = 1000
var w = 1000

@Suppress("DEPRECATION")
open class Snowflakes(ctx: Context) : View(ctx) {
    private lateinit var moveTask: MoveTask
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLUE)
        for (s in snow) {
            paint.color = s.color
            canvas.drawCircle(s.x, s.y, s.radius, paint)
        }

    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        h = bottom - top;
        w = right - left
        val r = Random(0)
        r.nextFloat()
        snow = Array(10) {
            Snowflake(
                x = r.nextFloat() * w,
                y = r.nextFloat() * h,
                velocity = 15 + 10 * r.nextFloat(),
                radius = 30 + 20 * r.nextFloat(),
                Color.argb(100 + (156 * r.nextFloat()).toInt(), 255, 255, 255)
            )
        }
        Log.d("mytag", "snow: " + snow.contentToString())
    }

    fun moveSnowflakes() {
        for (s in snow) {
            if (s.y < h) {
                s.y += s.velocity
            } else if (s.y > h / 2) {
                s.y += s.velocity / 100
            }
            if (s.y > h) {
                s.y -= h
            }
        }
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        moveTask = MoveTask(this)
        moveTask.execute(100)
        return false

    }

    @Suppress("UNREACHABLE_CODE", "DEPRECATION")
    class MoveTask(@SuppressLint("StaticFieldLeak") private val s: Snowflakes) :
        AsyncTask<Int, Int, Int>() {
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Int?): Int {
            val delay = params[0] ?: 200
            while (true) {
                Thread.sleep(delay.toLong())
                s.moveSnowflakes()
            }
            @Suppress("UNREACHABLE_CODE")
            return 0
        }
    }
}
