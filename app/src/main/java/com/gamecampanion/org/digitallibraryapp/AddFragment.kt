package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.firestore.DigitalLibraryModel
import com.gamecampanion.org.digitallibraryapp.Database.firestore.Firestore
import com.gamecampanion.org.digitallibraryapp.Database.firestore.FirestoreClientImpl
import kotlinx.android.synthetic.main.fragment_add.*

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
    lateinit var url: MultiAutoCompleteTextView
    var positionIndex: Int = 0
    lateinit var datePicker: DatePicker
    lateinit var firestore: Firestore
    lateinit var isCloudSaved: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.layoutmobileadd, container, false)

        typeSpinner = view.findViewById(R.id.typeSpinner)
        genreSpinner = view.findViewById(R.id.genreSpinner)
        platformSpinner = view.findViewById(R.id.platformSpinner)
        ratingBar = view.findViewById(R.id.ratingBar)
        name = view.findViewById(R.id.nameEditText)
        url = view.findViewById(R.id.urlImageText)
        datePicker = view.findViewById(R.id.calendarView)
        isCloudSaved = view.findViewById(R.id.isCloudSavedSwitch)

        databaseHelper = DatabaseHelper(view.context)
        firestore = FirestoreClientImpl(view)

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (view != null) {
                    positionIndex = position
                    configLayout(position)
                }
            }

        }

        var addButton: Button = view.findViewById(R.id.button_second)

        addButton.setOnClickListener {
            addButton.isEnabled = false
            addItemToDB(positionIndex)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun configLayout(position: Int) {
        when (position) {
            0 -> configLayout(resources.getStringArray(R.array.gamePlatform), true)
            1 -> configLayout(resources.getStringArray(R.array.musicGenre), false)
            2 -> configLayout(resources.getStringArray(R.array.movieGenre), false)
        }
    }

    private fun configLayout(genreSpinnerArray: Array<String>, isEnabled: Boolean) {
        genreSpinner.isEnabled = isEnabled

        platformSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            genreSpinnerArray
        )
    }

    private fun addItemToDB(position: Int) {

        if (isCloudSaved.isChecked) {
            firestore.writeToDatabase(digitalLibraryModel(), "digitallibrarydavidk")
        } else {
            when (position) {
                0 -> databaseHelper.runGameDBInsert(
                    databaseHelper.createItemGame(
                        name.text.toString(),
                        platformSpinner.selectedItem.toString(),
                        datePicker,
                        ratingBar,
                        genreSpinner.selectedItem.toString(),
                        true,
                        url.text.toString()
                    )
                )
                1 -> "databaseHelper.addItemMusic()"
                //2 -> databaseHelper.addItemMovie()
            }
        }
    }

    private fun digitalLibraryModel(): DigitalLibraryModel {
        return DigitalLibraryModel(
            genreSpinner.selectedItem.toString(),
            false,
            name.text.toString(),
            platformSpinner.selectedItem.toString(),
            ratingBar.rating.toLong(),
            datePicker.dayOfMonth.toString() + "/" + datePicker.month + "/" + datePicker.year,
            createCollectionsUrlList()
        )

    }

    private fun createCollectionsUrlList(): List<String>{
        return urlImageText.text.toString().split(",").toList()
    }


}


