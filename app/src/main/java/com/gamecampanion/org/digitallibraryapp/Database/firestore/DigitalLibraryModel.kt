package com.gamecampanion.org.digitallibraryapp.Database.firestore

data class DigitalLibraryModel(val genre: String?,
                               val ispreordered: Boolean?,
                               val name: String?,
                               val platform: String?,
                               val rating: Long?,
                               val releasedate: String?,
                               val images: List<String>?  ) {

}