package com.gamecampanion.org.digitallibraryapp

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageSwitcher
import com.gamecampanion.org.digitallibraryapp.Database.firestore.DigitalLibraryModel
import com.gamecampanion.org.digitallibraryapp.digitallibrary.ViewFunctions
import java.time.LocalDate
import java.util.regex.Pattern

class MyGestureDetectorListener(var switcher: ImageSwitcher, var view: View) :
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    lateinit var gameListFilter: List<DigitalLibraryModel>
    lateinit var filterList: List<String>
    private var viewFunctions = ViewFunctions()
    private var imageArrayCounter = 0

    fun setGamesList(list: List<String>) {
        this.filterList = list
    }

    fun setImageArrayCounter(counter: Int) {
        imageArrayCounter = counter
    }

    fun setGamesListFilter(gameListFilter: List<DigitalLibraryModel>) {
        this.gameListFilter = gameListFilter
    }

    override fun onShowPress(e: MotionEvent?) {

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

        println("smurf2")

        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {

        filterList?.get(imageArrayCounter)
            ?.let { viewFunctions.loadGameImageFromUrlLocal(it, switcher) }

        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {

        println("smurf2")

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

}