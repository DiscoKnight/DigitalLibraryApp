package com.gamecampanion.org.digitallibraryapp

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.digitallibrary.ViewFunctions
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.regex.Pattern
import android.view.ViewGroup as ViewGroup1

class FragmentView : Fragment() {

    lateinit var textViewInfo: TextView
    var gameList: List<GameEntity> = ArrayList()
    lateinit var gameListFilter: List<GameEntity>// = ArrayList()
    lateinit var dbHelper: DatabaseHelper
    lateinit var bitmapDrawable: BitmapDrawable
    lateinit var imgView: ImageView
    var counter: Int = 0
    lateinit var imageSwitcher: ImageSwitcher
    lateinit var typeFilterSpinner: Spinner
    lateinit var typeFilterResultSpinner: Spinner
    val viewFunction =
        ViewFunctions()

    val images = intArrayOf(
        R.drawable.gow1,
        R.drawable.hzd1
    );

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup1?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.viewcollectionlayout, container, false)

        imageSwitcher = view.findViewById(R.id.imageswitcher)

        textViewInfo = view.findViewById(R.id.infoView)

        typeFilterSpinner = view.findViewById(R.id.typeFilter)

        typeFilterResultSpinner = view.findViewById(R.id.typeFilterResult)
        typeFilterResultSpinner.isEnabled = false

        dbHelper = DatabaseHelper(view.context)

        gameList = dbHelper.getGamesFromDB()

        viewFunction.getInfoText(counter, gameList, textViewInfo)

        //loadImageFromUrl(gameList[counter])

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

        typeFilterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    when (position) {
                        0 -> setUpPlatform()
                        1 -> setUpRating()
                    }
                }
            }

        }

        return view
    }

    private fun buttonClick(view: View) {
        viewFunction.getInfoText(counter, gameListFilter, textViewInfo)
        loadImageFromUrl(gameListFilter[counter])

        var b = Pattern.compile("0/0/\\d{4}").matcher(gameListFilter[counter].releaseDate).find()

        if( (!Pattern.compile("0/0/\\d{4}").matcher(gameListFilter[counter].releaseDate).find()) &&
            viewFunction.calcuateTimeToRelease(gameListFilter[counter], LocalDate.now().toString()).isNegative){
            viewFunction.createAlertDialog(view.context, viewFunction.calcuateTimeToRelease(gameListFilter[counter],LocalDate.now().toString()))
        }

    }

    private fun setUpPlatform() {
        typeFilterResultSpinner.isEnabled = true

        typeFilterResultSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.gamePlatform)
        )

        typeFilterResultSpinner.onItemSelectedListener =
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
                        var filterResult = typeFilterResultSpinner.selectedItem as String

                        gameListFilter = viewFunction.filterByPlatform(filterResult,gameList)

                    }
                }

            }


    }

    private fun setUpRating() {
        typeFilterResultSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.ratingTypes)
        )

        typeFilterResultSpinner.onItemSelectedListener =
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
                        var filterResult = typeFilterResultSpinner.selectedItem as String

                        //viewFunction.filterByRating(Character.getNumericValue(filterResult[filterResult.length - 1].toInt()))

                        gameListFilter = viewFunction.filterByRating(Character.getNumericValue(filterResult[filterResult.length - 1].toInt()), gameList)

                    }
                }

            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun loadImageFromUrl(gameEntity: GameEntity) {

        runBlocking {
            var r = GlobalScope.async {
                bitmapDrawable =
                    Picasso.get().load(gameEntity.url).get().toDrawable(Resources.getSystem())
            }

            r.await()
        }

        imageSwitcher.setImageDrawable(bitmapDrawable.current)

    }

}