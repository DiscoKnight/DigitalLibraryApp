package com.gamecampanion.org.digitallibraryapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.gamecampanion.org.digitallibraryapp.Database.DatabaseHelper
import com.gamecampanion.org.digitallibraryapp.Database.firestore.DigitalLibraryModel
import com.gamecampanion.org.digitallibraryapp.Database.firestore.FirestoreClientImpl
import com.gamecampanion.org.digitallibraryapp.R

class DeleteFragment : Fragment() {

    private lateinit var button: Button
    private lateinit var deleteSpinner: Spinner
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var firestoreClientImpl: FirestoreClientImpl
    private var indexDeleteSpinner = 0

    var li1 = ArrayList<DigitalLibraryModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.layoutmobiledeletefragment, container, false)

        button = view.findViewById(R.id.deleteImageButton)
        deleteSpinner = view.findViewById(R.id.deleteSpinner)
        firestoreClientImpl = FirestoreClientImpl(view)

        databaseHelper = DatabaseHelper(view.context)
        firestoreClientImpl.getFromDatabase("digitallibrarydavidk")

        button.setOnClickListener {
            button.isEnabled = false
            delete()
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

    private fun createAndPopulate(): Array<DigitalLibraryModel> {

        //firestoreClientImpl.getFromDatabase("digitallibrarydavidk")

        li1 = firestoreClientImpl.getCloudCollectionList()

        return li1.toTypedArray()
    }

    private fun delete() {
        indexDeleteSpinner = deleteSpinner.selectedItemId as Int

        firestoreClientImpl.deleteFromCloudCollection(
            firestoreClientImpl.documentIDList[indexDeleteSpinner].toString(),
            "digitallibrarydavidk"
        )

    }

}