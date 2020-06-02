package com.gamecampanion.org.digitallibraryapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import android.view.ViewGroup as ViewGroup1

class FragmentView : Fragment() {

    lateinit var gameList: List<GameEntity>
    var imageUrlList: List<String> = ArrayList()

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
        var counter: Int = 0

        val imageSwitcher: ImageSwitcher = view.findViewById(R.id.imageswitcher)
        imageSwitcher.setFactory {
            val imgView = ImageView(this.context)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setPadding(8, 8, 8, 8)
            imgView
        }
        imageSwitcher.setImageResource(images[counter])

        val leftButton: ImageButton = view.findViewById(R.id.imageButtonLeft)

        leftButton.setOnClickListener {

            if (counter == (images.size - 1)) {
                counter = 0
                imageSwitcher.setImageResource(images[counter])
            } else {
                imageSwitcher.setImageResource(images[counter.inc()])
                counter = counter.inc()
            }
        }

        runBlocking {
            GlobalScope.launch {
                foo(view.context)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /////////////////////////////////////////////////
    suspend fun foo(context: Context){
        gameList = DatabaseHelper(context).getGamesFromDB()
        getImageURLArray(gameList)
    }

    fun getImageURLArray(gameList: List<GameEntity>) {

        gameList.forEach { e ->
            e.url
            imageUrlList.plus(e)
        }

    }


}