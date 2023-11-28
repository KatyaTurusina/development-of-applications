package com.example.colortiles

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import java.util.*

class MyView(context: Context?) : View(context) {
    private val p = Paint()
    private val r = Random()
    private var winner = false
    private var width = -1 // layout size
    private var height = -1
    private val numOfTiles = 4
    private val tiles = Array(numOfTiles) { BooleanArray(numOfTiles) { r.nextBoolean() } }
    private var tileSize = 100f
    private var padding = 10f
    
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        width = right - left; height = bottom - top
    }

    override fun onDraw(canvas: Canvas) {
        var countTiles = 0
        tileSize = (width * 0.88 / numOfTiles).toFloat()
        padding = (width * 0.1 / numOfTiles).toFloat()
        canvas.apply {
            drawColor(Color.DKGRAY)
            for (i in 0 until numOfTiles) {
                for (j in 0 until numOfTiles) {
                    val tile = tiles[i][j]
                    p.color = if (tile) {
                        Color.rgb(230, 82, 79)
                    } else {
                        Color.rgb(68, 219, 116)
                    }
                    countTiles += if (tile) 1 else 0
                    drawRoundRect(
                        j * tileSize + (j + 1f) * padding,
                        i * tileSize + (i + 1f) * padding,
                        (j + 1f) * (padding + tileSize),
                        (i + 1f) * (padding + tileSize),
                        20f, 20f, p
                    )
                }
            }
        }
        winner = countTiles == 0 || countTiles == numOfTiles * numOfTiles
        if (winner) {Toast.makeText(context, "Win!", Toast.LENGTH_SHORT).show()}
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (winner) return false
        event?.apply {
            if (action != MotionEvent.ACTION_DOWN) return false
            var rectX: Int? = null
            var rectY: Int? = null
            for (i in 0 until numOfTiles) {
                for (j in 0 until numOfTiles) {
                    val left = j * tileSize + (j + 1f) * padding
                    val top = i * tileSize + (i + 1f) * padding
                    val right = (j + 1f) * (padding + tileSize)
                    val bottom = (i + 1f) * (padding + tileSize)
                    if (x in left..right && y in top..bottom) {
                        rectX = i
                        rectY = j
                        break
                    }
                }
            }
            if (rectX != null && rectY != null) { changeColorOfTile(rectX, rectY) }
        }
        invalidate()
        return true
    }

    private fun changeColorOfTile(x: Int, y: Int) {
        tiles[x][y] = !tiles[x][y]
        for (i in 0 until numOfTiles) {
            tiles[x][i] = !tiles[x][i]
            tiles[i][y] = !tiles[i][y]
        }
    }
}
