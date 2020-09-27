package com.gamecampanion.org.digitallibraryapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.R
import java.util.stream.Collectors

class EditFragment : Fragment() {

    lateinit var collectionTypeSpinner: Spinner
    lateinit var collectionSelectionResultSpinner: Spinner
    lateinit var nameTextView: EditText
    lateinit var urlTextView: EditText
    lateinit var dateReleasePicker: DatePicker
    lateinit var acceptEditButton: Button
    lateinit var entity: GameEntity
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.layoutmobileedit, container, false)

        collectionTypeSpinner = view.findViewById(R.id.collectionSelectionSpinner)
        collectionSelectionResultSpinner = view.findViewById(R.id.collectionSelectionResultSpinner)
        nameTextView = view.findViewById(R.id.nameTextViewEdit)
        urlTextView = view.findViewById(R.id.urlTextViewEdit)
        dateReleasePicker = view.findViewById(R.id.releaseDatePicker)
        acceptEditButton = view.findViewById(R.id.editAcceptButton)

        databaseHelper = DatabaseHelper(view.context)

        collectionTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when (position) {
                    0 -> fooGame(view)
                    1 -> ""
                    2 -> ""
                }

            }

        }

        acceptEditButton.setOnClickListener { onClick(view) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun fooGame(view: View?) {

        var gameArr =
            databaseHelper.getGamesFromDB().stream().collect(Collectors.toList()).toTypedArray()

        collectionSelectionResultSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            gameArr
        )

        collectionSelectionResultSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                entity = databaseHelper.getGamesFromDB().get(position)

                nameTextView.setText(entity.gameName)
                urlTextView.setText(entity.url)

                var releasedate = entity.releaseDate?.split("/")

                var realeaseDateArray = releasedate?.stream()?.mapToInt { e-> convertToInt(e)  }?.toArray()

                var day = realeaseDateArray?.get(1)
                var month = realeaseDateArray?.get(0)
                var year = realeaseDateArray?.get(2)

                if (month != null
                    && year != null
                    && day != null) {
                    dateReleasePicker.updateDate(year, month, day)
                }


            }
        }

    }

    private fun convertToInt(str: String): Int{
        return Integer.valueOf(str)
    }

    private fun onClick(view: View){
        entity.gameName = nameTextView.text.toString()
        entity.url = urlTextView.text.toString()
        entity.releaseDate = Integer.toString(dateReleasePicker.month) + "/" + dateReleasePicker.dayOfMonth + "/" + dateReleasePicker.year

        databaseHelper.editItemGame(entity)



    }


}