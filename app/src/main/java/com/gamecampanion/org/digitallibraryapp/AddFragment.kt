package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallable
import com.gamecampanion.org.digitallibraryapp.digitallibrary.game.GameDao_Impl
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.DateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {


    lateinit var typeSpinner: Spinner

    lateinit var genreSpinner: Spinner

    lateinit var platformSpinner: Spinner

    lateinit var ratingBar: RatingBar

    lateinit var name: EditText

    lateinit var url: EditText

    var positionIndex: Int = 0

    lateinit var datePicker: DatePicker

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add, container, false)

        typeSpinner = view.findViewById(R.id.typeSpinner)

        genreSpinner = view.findViewById(R.id.genreSpinner)

        platformSpinner = view.findViewById(R.id.platformSpinner)

        ratingBar = view.findViewById(R.id.ratingBar)

        name = view.findViewById(R.id.nameEditText)

        url = view.findViewById(R.id.urlImageText)

        datePicker = view.findViewById(R.id.calendarView)

        ///////////////////////////////////////////
        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                print("smurf")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (view != null) {
                    positionIndex = position
                    configLayout(position)
                }
            }

        }

        /////////////////////////////////////////

        var addButton: Button = view.findViewById(R.id.button_second);

        addButton.setOnClickListener {
            addItemToDB(positionIndex)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun configLayout(position : Int){
        when(position){
            0 -> configLayout(resources.getStringArray(R.array.gamePlatform), true)
            1 -> configLayout(resources.getStringArray(R.array.musicGenre), false)
            2 -> configLayout(resources.getStringArray(R.array.movieGenre), false)
        }
    }

    private fun configLayout(genreSpinnerArray: Array<String>, isEnabled: Boolean){
            genreSpinner.isEnabled = isEnabled

            platformSpinner.adapter = ArrayAdapter(this.requireContext(),
                android.R.layout.simple_spinner_item,
                genreSpinnerArray)
    }

    private fun addItemToDB(position: Int){

        when(position){
            0 -> addItemGame()
            1 -> print("")
            2 -> print("")
        }
    }

    private fun addItemGame(){
        var platformName = platformSpinner.selectedItem.toString()
        var name = nameEditText.text.toString()

        var day: String = datePicker.month.toString() + "/" + datePicker.month + "/" + datePicker.year

        val entity: GameEntity = GameEntity(0,
            name,
            platformName,
            day,
            ratingBar.numStars,
            platformName,
            true)

        print("smurf")

        var v = foo(entity)

    }

    private fun foo(entity: GameEntity) : Job {
        //createGameDatabase().gameDao().insertGame(entity)

        return runBlocking {
            GlobalScope.launch(){
                CoroutinesRoom.execute(createGameDatabase(), true, CollectionCallable(createGameDatabase(), entity))
            }
        }

    }

    private fun createMusicDatabase(): MusicDB{
        return Room.databaseBuilder(
            this.requireContext(),
            MusicDB::class.java, "musicDB"
        ).build()
    }

    private fun createGameDatabase() : GameDB{
        return Room.databaseBuilder(
            this.requireContext(),
            GameDB::class.java, "gameDB"
        ).build()
    }

    private fun createMovieDatabase() : MovieDB{
        return Room.databaseBuilder(
            this.requireContext(),
            MovieDB::class.java, "movieDB"
        ).build()
    }

}


