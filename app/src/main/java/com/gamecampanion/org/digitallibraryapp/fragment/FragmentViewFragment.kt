package com.gamecampanion.org.digitallibraryapp.fragment

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.CollectionTypes
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.firestore.DigitalLibraryModel
import com.gamecampanion.org.digitallibraryapp.Database.firestore.Firestore
import com.gamecampanion.org.digitallibraryapp.Database.firestore.FirestoreClientImpl
import com.gamecampanion.org.digitallibraryapp.MyGestureDetectorListener
import com.gamecampanion.org.digitallibraryapp.R
import com.gamecampanion.org.digitallibraryapp.digitallibrary.ViewFunctions
import java.time.LocalDate
import java.util.regex.Pattern
import java.util.stream.Collectors
import android.view.ViewGroup as ViewGroup1

class FragmentViewFragment : Fragment() {

    var gameListFilter: List<DigitalLibraryModel> = ArrayList()
    var gamesFromFireStore: List<DigitalLibraryModel> = ArrayList()
    var counter: Int = 0
    var isEnlarged = true
    private var imageArrayCounter = 0

    lateinit var dbHelper: DatabaseHelper
    lateinit var imgView: ImageView
    lateinit var imageSwitcher: ImageSwitcher
    lateinit var typeFilterSpinner: Spinner
    lateinit var typeFilterResultSpinner: Spinner
    lateinit var collectionTypeFilter: Spinner
    lateinit var firestore: Firestore
    lateinit var gesture: GestureDetector
    lateinit var myGestureDetectorListener: MyGestureDetectorListener

    private var type: CollectionTypes = CollectionTypes.GAME

    val viewFunction = ViewFunctions()
    val images = intArrayOf(
        R.drawable.gow1,
        R.drawable.hzd1
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup1?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.layoutmobileview, container, false)

        imageSwitcher = view.findViewById(R.id.imageswitcher)
        typeFilterSpinner = view.findViewById(R.id.typeFilter)
        typeFilterResultSpinner = view.findViewById(R.id.typeFilterResult)
        collectionTypeFilter = view.findViewById(R.id.collectionTypeFilter)

        dbHelper = DatabaseHelper(view.context)

        firestore = FirestoreClientImpl(view)

        firestore.getFromDatabase("digitallibrarydavidk")

        gamesFromFireStore = firestore.getCloudCollectionList()

        imageSwitcher.setFactory {
            imgView = ImageView(this.context)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setPadding(8, 8, 8, 8)
            imgView
        }

        imageSwitcher.setImageResource(images[counter])

        val leftButton: ImageButton = view.findViewById(R.id.imageButtonLeft)

        leftButton.setOnClickListener {

            counter = counter.inc()

            if (counter <= gameListFilter.size - 1) {
                buttonClick(view)
            } else {
                counter = 0
                buttonClick(view)

            }

        }

        collectionTypeFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    when (position) {
                        0 -> setUpSpinner(R.array.gamePlatform)
                        1 -> setUpSpinner(R.array.musicGenre)
                        2 -> setUpSpinner(R.array.movieGenre)
                    }
                }
            }
        }

        typeFilterResultSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (view != null &&
                        !gameListFilter.isNullOrEmpty()
                    ) {

                        when (type) {
                            CollectionTypes.GAME -> viewFunction.filterByRating(
                                Integer.valueOf(typeFilterResultSpinner.selectedItem as String)
                                , gamesFromFireStore
                            )
                            CollectionTypes.MOVIE -> ""
                            CollectionTypes.MUSIC -> ""
                        }

                    }
                }

            }

        imageSwitcher.setOnClickListener { onClick(view) }

        myGestureDetectorListener = MyGestureDetectorListener(imageSwitcher, view)

        view.setOnTouchListener { v, event -> onTouchEvent(v, event) }

        return view
    }

    private fun onTouchEvent(view: View, event: MotionEvent): Boolean {
        if (gameListFilter.isNotEmpty() &&
            gameListFilter[counter].images!!.isNotEmpty()) {
            myGestureDetectorListener.setGamesListFilter(gameListFilter)

            myGestureDetectorListener.setGamesList(createImageList(gameListFilter[counter]))

            if (imageArrayCounter < gameListFilter[counter].images!!.size) {
                myGestureDetectorListener.setImageArrayCounter(imageArrayCounter)
            } else {
                imageArrayCounter = 0
            }

            gesture = GestureDetector(view.context, myGestureDetectorListener)

            gesture.onTouchEvent(event)

            imageArrayCounter = imageArrayCounter.inc()
        }

        return true
    }

    private fun createImageList(digitalLibraryModel: DigitalLibraryModel): List<String> {

        return digitalLibraryModel.images?.stream()?.filter { e -> e.isNotEmpty() }
            ?.collect(Collectors.toList())!!.toMutableList()

    }

    private fun setUpSpinner(genreArray: Int) {
        typeFilterSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(genreArray)
        )

        typeFilterSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (view != null) {
                        var filterResult = typeFilterSpinner.selectedItem as String

                        gameListFilter =
                            viewFunction.filterByPlatform(filterResult, gamesFromFireStore)

                    }
                }

            }

    }

    private fun buttonClick(view: View) {

        imageArrayCounter = 0

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onClick(view: View) {

        if (isEnlarged) {
            imageSwitcher.animate().scaleX(1.75f).scaleY(1.75f).start()
            isEnlarged = false
        } else {
            imageSwitcher.animate().scaleX(0.75f).scaleY(0.75f).start()
            isEnlarged = true
        }

    }

}