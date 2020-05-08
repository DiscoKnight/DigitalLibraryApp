package com.gamecampanion.org.digitallibraryapp.Database.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gamecampanion.org.digitallibraryapp.digitallibrary.genres.MovieGenreEnum
import java.time.LocalDate

@Entity
class MovieEntity(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "movieTitle") val artist: String?,
                  @ColumnInfo(name = "releaseDate") val releaseDate: LocalDate?,
                  @ColumnInfo(name = "rating") val rating: Int?,
                  @ColumnInfo(name = "genre") val genre: MovieGenreEnum?) {
}