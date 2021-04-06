package com.gamecampanion.org.digitallibraryapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.R
import com.gamecampanion.org.digitallibraryapp.game.GameFunctions
import com.gamecampanion.org.digitallibraryapp.game.GameFunctionsImpl
import com.gamecampanion.org.digitallibraryapp.httprestclient.MyRESTClient
import com.gamecampanion.org.digitallibraryapp.model.GameRestModel
import okhttp3.Request

class FragmentViewRESTFragment: Fragment() {

    lateinit var gameFunctions: GameFunctions
    lateinit var imgView: ImageView
    lateinit var imageSwitcher: ImageSwitcher
    lateinit var typeFilterSpinner: Spinner
    lateinit var typeFilterResultSpinner: Spinner
    lateinit var collectionTypeFilter: Spinner

    var gameUrl: String = "http://localhost:8080/api/v1.0/mongo/game/getAllGames"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameFunctions = GameFunctionsImpl(MyRESTClient())

        return inflater.inflate(R.layout.layoutmobileview, container, false)

    }

    fun getAllGames(): List<GameRestModel>{

        return gameFunctions.getGameList(Request.Builder()
            .get()
            .url(gameUrl)
            .build())
    }
}