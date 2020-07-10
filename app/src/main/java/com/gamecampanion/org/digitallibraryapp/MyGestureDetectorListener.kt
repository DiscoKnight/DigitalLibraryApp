package com.gamecampanion.org.digitallibraryapp

import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageSwitcher
import androidx.core.view.GestureDetectorCompat
import com.gamecampanion.org.digitallibraryapp.digitallibrary.ViewFunctions

class MyGestureDetectorListener(var switcher: ImageSwitcher): GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    lateinit var filterList: List<String>
    private var viewFunctions = ViewFunctions()
    private var imageArrayCounter = 0

    fun setGamesList(list: List<String>){
        this.filterList = list
    }

    fun setImageArrayCounter(counter: Int){
        imageArrayCounter = counter
    }

    override fun onShowPress(e: MotionEvent?) {
        println("smurf1")

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {

        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {

        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {

        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {

        //if(imageArrayCounter < filterList.size ){
            filterList?.get(imageArrayCounter)?.let { viewFunctions.loadGameImageFromUrlLocal(it, switcher) }
            //imageArrayCounter = imageArrayCounter.inc()
        //}

        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {

        if(imageArrayCounter < filterList.size ){
            filterList?.get(imageArrayCounter)?.let { viewFunctions.loadGameImageFromUrlLocal(it, switcher) }
            imageArrayCounter = imageArrayCounter.inc()
        }

        if(imageArrayCounter == filterList.size){
            imageArrayCounter = 0
            filterList?.get(imageArrayCounter)?.let { viewFunctions.loadGameImageFromUrlLocal(it, switcher) }
        }

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }
}