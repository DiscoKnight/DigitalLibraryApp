package com.gamecampanion.org.digitallibraryapp

import android.view.GestureDetector
import android.view.MotionEvent

class MyGestureDetector: GestureDetector.OnGestureListener {
    override fun onShowPress(e: MotionEvent?) {
        TODO("Not yet implemented")
        println("smurf1")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
        println("smurf2")
    }

    override fun onDown(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
        println("smurf3")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        TODO("Not yet implemented")
        println("smurf4")
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        TODO("Not yet implemented")
        println("smurf5")
    }

    override fun onLongPress(e: MotionEvent?) {
        TODO("Not yet implemented")
        println("smurf6")
    }
}