package com.gamecampanion.org.digitallibraryapp.Database.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gamecampanion.org.digitallibraryapp.digitallibrary.genres.GameGenreEnum
import java.time.LocalDate

@Entity
data class GameEntity(@PrimaryKey val id: Int,
                      @ColumnInfo(name = "gameName") val gameName: String?,
                      @ColumnInfo(name = "platform") val platform: String?,
                      @ColumnInfo(name = "releaseDate") val releaseDate: String ?,
                      @ColumnInfo(name = "rating") val rating: Int?,
                      @ColumnInfo(name = "genre") val genre: String?,
                      @ColumnInfo(name = "isPreOrdered") val isPreordered: Int?)