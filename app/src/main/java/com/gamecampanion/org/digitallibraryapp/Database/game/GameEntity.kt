package com.gamecampanion.org.digitallibraryapp.Database.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(@PrimaryKey(autoGenerate = true) val id: Int,
                      @ColumnInfo(name = "gameName") var gameName: String?,
                      @ColumnInfo(name = "platform") val platform: String?,
                      @ColumnInfo(name = "releaseDate") var releaseDate: String?,
                      @ColumnInfo(name = "rating") val rating: Int?,
                      @ColumnInfo(name = "genre") val genre: String?,
                      @ColumnInfo(name = "isPreOrdered") val isPreordered: Boolean?,
                      @ColumnInfo(name = "urlString") var url: String?){

    override fun toString(): String {
        return gameName.toString()
    }
}