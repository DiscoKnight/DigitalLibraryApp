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
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.stream.Collectors
import android.view.ViewGroup as ViewGroup1

class FragmentView : Fragment() {

    lateinit var textViewInfo: TextView
    lateinit var gameList: List<GameEntity>// = ArrayList()
    lateinit var gameListFilter: List<GameEntity>
    lateinit var dbHelper: DatabaseHelper
    lateinit var bitmapDrawable: BitmapDrawable
    lateinit var imgView: ImageView
    var counter: Int = 0
    lateinit var imageSwitcher: ImageSwitcher
    lateinit var typeFilterSpinner: Spinner
    lateinit var typeFilterResultSpinner: Spinner

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

        getInfoText(counter, gameList)

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

            filterByRating(4)

            counter = counter.inc()

            if(counter <= gameListFilter.size - 1){
                getInfoText(counter, gameListFilter)
                loadImageFromUrl(gameListFilter[counter])
            }
            else{
                counter = 0
                getInfoText(counter, gameListFilter)
                loadImageFromUrl(gameListFilter[counter])
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
                    when(position){
                        0 -> setUpPlatform()
                        1 -> setUpRating()
                    }
                }
            }

        }

        return view
    }

    private fun setUpPlatform(){
        typeFilterResultSpinner.isEnabled = true

        typeFilterResultSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.gamePlatform)
        )

        typeFilterResultSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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

                    filterByPlatform(filterResult)

                }
            }

        }


    }

    private fun setUpRating(){
        typeFilterResultSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.ratingTypes)
        )

        typeFilterResultSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

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

                    filterByRating(Character.getNumericValue(filterResult[filterResult.length - 1].toInt()))

                }
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getInfoText(counter: Int, gameList: List<GameEntity>) {
        textViewInfo.setText(gameList[counter].gameName + "\n" + gameList[counter].platform + "\n" + gameList[counter].genre)
    }

    private fun loadImageFromUrl(gameEntity: GameEntity){

        runBlocking {
            var r = GlobalScope.async {
                bitmapDrawable = Picasso.get().load(gameEntity.url).get().toDrawable(Resources.getSystem())
            }

            r.await()
        }

        imageSwitcher.setImageDrawable(bitmapDrawable.current)

    }

    private fun filterByPlatform(platform: String){
        gameList = dbHelper.getGamesFromDB()

        gameListFilter = gameList.stream().filter { e -> e.platform.equals(platform)}.collect(Collectors.toList())

    }

    private fun filterByRating(rating: Int){
        gameList = dbHelper.getGamesFromDB()

        gameList = gameList.stream().filter { e -> e.rating!! >= rating}.collect(Collectors.toList())

    }


}