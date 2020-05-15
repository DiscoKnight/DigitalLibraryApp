package com.gamecampanion.org.digitallibraryapp.Database.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieEntity(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "movieTitle") val artist: String?,
                  @ColumnInfo(name = "releaseDate") val releaseDate: String?,
                  @ColumnInfo(name = "rating") val rating: Int?,
                  @ColumnInfo(name = "genre") val genre: String?,
                  @ColumnInfo(name = "isPreOrdered") val isPreordered: Int?) {
}