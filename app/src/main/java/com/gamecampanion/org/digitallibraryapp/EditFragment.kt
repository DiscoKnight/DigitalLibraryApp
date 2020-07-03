package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import java.util.stream.Collectors

class EditFragment : Fragment() {

    lateinit var collectionTypeSpinner: Spinner
    lateinit var collectionSelectionResultSpinner: Spinner
    lateinit var nameTextView: EditText

    lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_edit, container, false)

        collectionTypeSpinner = view.findViewById(R.id.collectionSelectionSpinner)
        collectionSelectionResultSpinner = view.findViewById(R.id.collectionSelectionResultSpinner)
        nameTextView = view.findViewById(R.id.nameTextViewEdit)

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
                var entity = databaseHelper.getGamesFromDB().get(position)

                nameTextView.setText(entity.gameName)

            }
        }

    }


}