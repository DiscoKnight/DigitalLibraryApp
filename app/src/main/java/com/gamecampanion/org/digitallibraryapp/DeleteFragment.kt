package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper

class DeleteFragment : Fragment() {

    lateinit var button: Button
    lateinit var deleteSpinner: Spinner
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.deletefragment, container, false)

        button = view.findViewById(R.id.deleteImageButton)
        deleteSpinner = view.findViewById(R.id.deleteSpinner)

        databaseHelper = DatabaseHelper(view.context)

        button.setOnClickListener {
            button.isEnabled = false

            var entityName = deleteSpinner.selectedItem as String

            delete(entityName)
        }

        deleteSpinner.adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item,
            createAndPopulate()
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun createAndPopulate(): Array<Any> {
        var spinnerDeleteList = ArrayList<String>()

        for (name in databaseHelper.getGamesFromDB()) {
            name.gameName?.toUpperCase()?.let { spinnerDeleteList.add(it) }
        }

        return spinnerDeleteList.toArray()
    }

    private fun delete(name: String) {

        var entity = databaseHelper.getGamesFromDB().stream()
            .filter { e -> e.gameName!!.toUpperCase()!!.contentEquals(name) }.findFirst()

        entity?.let { databaseHelper.deleteItemGame(it.get()) }
    }

}