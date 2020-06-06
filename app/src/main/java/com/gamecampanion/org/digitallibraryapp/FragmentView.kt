package com.gamecampanion.org.digitallibraryapp

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewcollectionlayout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.net.URI
import java.util.*
import android.view.ViewGroup as ViewGroup1

class FragmentView : Fragment() {

    lateinit var textViewInfo: TextView
    var gameList: List<GameEntity> = ArrayList()
    lateinit var dbHelper: DatabaseHelper
    lateinit var bitmapDrawable: BitmapDrawable
    lateinit var imgView: ImageView

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

        textViewInfo = view.findViewById(R.id.infoView);

        dbHelper = DatabaseHelper(view.context)

        gameList = dbHelper.getGamesFromDB()

        var counter: Int = 0

        getInfoText(counter)

        val imageSwitcher: ImageSwitcher = view.findViewById(R.id.imageswitcher)
        imageSwitcher.setFactory {
            imgView = ImageView(this.context)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setPadding(8, 8, 8, 8)
            imgView
        }
        imageSwitcher.setImageResource(images[counter])

        val leftButton: ImageButton = view.findViewById(R.id.imageButtonLeft)

        leftButton.setOnClickListener {

//            if (counter == (images.size - 1)) {
//                counter = 0
//                imageSwitcher.setImageResource(images[counter])
//                getInfoText(counter)
//            } else {
//                imageSwitcher.setImageResource(images[counter.inc()])
//                counter = counter.inc()
//                getInfoText(counter)
//            }

            counter = counter.inc()

            while(counter > 0 && counter < gameList.size){
                getInfoText(counter)
                loadImageFromUrl(gameList[counter])
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getInfoText(counter: Int) {
        textViewInfo.setText(gameList[counter].gameName + "\n" + gameList[counter].platform + "\n" + gameList[counter].genre)
    }

    private fun loadImageFromUrl(gameEntity: GameEntity){

        runBlocking {
            var r = GlobalScope.async {
                bitmapDrawable = Picasso.get().load(gameEntity.url).get().toDrawable(Resources.getSystem())
            }

            r.await()
        }

        imageswitcher.setImageDrawable(bitmapDrawable.current)

        print("smurf1")

    }


}