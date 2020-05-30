package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieEntity
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallableGame
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallableMovie
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallableMusic
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {

    lateinit var databaseHelper: DatabaseHelper

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

        databaseHelper = DatabaseHelper(view.context)

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
            0 -> databaseHelper.addItemGame("","", datePicker, ratingBar, "", true, "")
            1 -> databaseHelper.addItemMusic()
            2 -> databaseHelper.addItemMovie()
        }
    }

}


