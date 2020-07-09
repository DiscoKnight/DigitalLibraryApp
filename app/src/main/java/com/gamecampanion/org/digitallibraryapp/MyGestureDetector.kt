package com.gamecampanion.org.digitallibraryapp

import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageSwitcher
import androidx.core.view.GestureDetectorCompat
import com.gamecampanion.org.digitallibraryapp.digitallibrary.ViewFunctions

class MyGestureDetector( var imageList : List<String>?, var switcher: ImageSwitcher): GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private var viewFunctions = ViewFunctions()
    private var imageArrayCounter = 0

    override fun onShowPress(e: MotionEvent?) {
        println("smurf1")

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        println("smurf2")

        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        println("smurf3")

        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        println("smurf4")

        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        println("smurf5")

        if(imageList?.size!! <= imageArrayCounter){
            imageArrayCounter.inc()
            imageList?.get(imageArrayCounter)?.let { viewFunctions.loadGameImageFromUrlLocal(it, switcher) }
        }else{
            imageArrayCounter = 0
        }

        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        println("smurf6")
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        println("smurf7")

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }
}