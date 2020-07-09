package com.gamecampanion.org.digitallibraryapp.Database.firestore

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreClientImpl(var view: View) : Firestore {

    var db = Firebase.firestore

    var arrayList = ArrayList<DigitalLibraryModel>()

    override fun getFromDatabase(collectionPath: String) {
        db.collection(collectionPath).get().addOnSuccessListener { result ->
            getDocumentFromResult(result)
        }.addOnFailureListener { e -> errorHandle(e, view) }

    }

    override fun writeToDatabase(model: DigitalLibraryModel, collectionPath: String) {
        db.collection(collectionPath).add(model).addOnSuccessListener { result -> successfullWrite(result) }
            .addOnFailureListener { e -> errorHandle(e, view) }

    }

    override fun getCloudCollectionList(): List<DigitalLibraryModel>{
        return this.arrayList
    }

    private fun successfullWrite(document: DocumentReference) {
        Log.i("", document.id)
    }

    private fun errorHandle(exception: Exception, view: View) {
        Log.d("firestore", exception.message)

        FirebaseAnalytics.getInstance(view.context).logEvent("firebasecollection", Bundle.EMPTY)
    }

    private fun getDocumentFromResult(querySnapshot: QuerySnapshot) {
        for (document in querySnapshot) {

            var document = DigitalLibraryModel(
                document.get("genre") as String,
                document.get("ispreordered") as Boolean,
                document.get("name") as String,
                document.get("platform") as String,
                document.get("rating") as Long,
                document.get("releasedate") as String,
                document.get("images") as List<String>
            )

            Log.d(TAG, "${document.name} => ${document}")

            arrayList.add(document)

        }

    }
}