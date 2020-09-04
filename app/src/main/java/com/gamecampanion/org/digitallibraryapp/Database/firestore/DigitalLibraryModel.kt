package com.gamecampanion.org.digitallibraryapp.Database.firestore

data class DigitalLibraryModel(val genre: String?,
                               val ispreordered: Boolean?,
                               var name: String?,
                               val platform: String?,
                               val rating: Long?,
                               val releasedate: String?,
                               val images: List<String>?  ) {

    override fun toString(): String {
        return this.name.toString()
    }

}