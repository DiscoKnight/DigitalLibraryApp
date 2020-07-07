package com.gamecampanion.org.digitallibraryapp.Database.firestore

import android.view.View

interface Firestore {

    fun getFromDatabase(view: View)

    fun writeToDatabase()
}