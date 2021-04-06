package com.gamecampanion.org.digitallibraryapp.model

data class GameRestModel(
    val id: String,
    val gameName: String,
    val gameGenre: String,
    val rating: Int,
    val preOrder: Boolean,
    val imageUrl: String,
    val releaseDate: String,
    val gamePublisher: PublisherModel
) {

}