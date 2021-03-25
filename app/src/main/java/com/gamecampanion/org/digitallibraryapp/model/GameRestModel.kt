package com.gamecampanion.org.digitallibraryapp.model

data class GameRestModel(
    val id: String,
    val gameName: String,
    val gameGenre: String,
    val rating: Int,
    val preOrder: Boolean,
    val imageUrl: String
) {


//    private val id = idP;
//    private val gameName = gameNameP;
//    private val gameGenre = gameGenreP;
////    private val gamePublisher: PublisherModel? = null
//    private val rating = ratingP;
//    private val preOrder = preOrderP;
//    private val imageUrl = imageUrlP;
}