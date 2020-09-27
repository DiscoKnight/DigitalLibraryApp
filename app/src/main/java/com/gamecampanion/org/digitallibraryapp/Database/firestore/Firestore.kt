package com.gamecampanion.org.digitallibraryapp.Database.firestore

interface Firestore {

    fun getFromDatabase(collectionPath: String)

    fun writeToDatabase(model: DigitalLibraryModel, collectionPath: String)

    fun getCloudCollectionList(): List<DigitalLibraryModel>

    fun deleteFromCloudCollection(documentId: String, collectionPath: String)
}