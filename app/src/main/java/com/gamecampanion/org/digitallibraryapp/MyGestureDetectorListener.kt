package com.gamecampanion.org.digitallibraryapp

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageSwitcher
import com.gamecampanion.org.digitallibraryapp.Database.firestore.DigitalLibraryModel
import com.gamecampanion.org.digitallibraryapp.digitallibrary.ViewFunctions
import java.time.LocalDate
import java.util.regex.Pattern

class MyGestureDetectorListener(var switcher: ImageSwitcher) : GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    lateinit var filterList: List<String>
    private var viewFunctions = ViewFunctions()
    private var imageArrayCounter = 0

    fun setGamesList(list: List<String>) {
        this.filterList = list
    }

    fun setImageArrayCounter(counter: Int) {
        imageArrayCounter = counter
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

        if(e2?.rawY?.compareTo(e2.y)!! > 0){
            println("smurf1")
        }

        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {

        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    private fun foo(gameListFilter: List<DigitalLibraryModel>,
                    viewFunction: ViewFunctions,
                    view: View,
                    counter: Int,
                    imageSwitcher: ImageSwitcher,
                    gamesFromFireStore: List<DigitalLibraryModel>){
        if (gameListFilter.isNotEmpty()) {
            gameListFilter[counter].images?.get(0).let {
                if (it != null) {
                    viewFunction.loadGameImageFromUrlLocal(it, imageSwitcher)
                }
            }

            if ((!Pattern.compile("0/0/\\d{4}").matcher(gamesFromFireStore[counter].releasedate)
                    .find()) &&
                viewFunction.calcuateTimeToRelease(
                    gameListFilter[counter].releasedate,
                    LocalDate.now().toString()
                ).isNegative
            ) {
                viewFunction.createAlertDialogPreOwned(
                    view.context,
                    viewFunction.calcuateTimeToRelease(
                        gameListFilter[counter].releasedate,
                        LocalDate.now().toString()
                    ),
                    gameListFilter[counter]
                )
            } else {
                viewFunction.createAlertDialog(gameListFilter[counter], view.context)
            }
        }
    }
}