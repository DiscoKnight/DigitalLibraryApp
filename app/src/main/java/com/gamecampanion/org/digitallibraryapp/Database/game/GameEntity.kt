package com.gamecampanion.org.digitallibraryapp.Database.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(@PrimaryKey(autoGenerate = true) val id: Int,
                      @ColumnInfo(name = "gameName") val gameName: String?,
                      @ColumnInfo(name = "platform") val platform: String?,
                      @ColumnInfo(name = "releaseDate") val releaseDate: String?,
                      @ColumnInfo(name = "rating") val rating: Int?,
                      @ColumnInfo(name = "genre") val genre: String?,
                      @ColumnInfo(name = "isPreOrdered") val isPreordered: Boolean?,
                      @ColumnInfo(name = "urlString") val url: String?)