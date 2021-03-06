package com.gamecampanion.org.digitallibraryapp.Database.music

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MusicEntity(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "artist") val artist: String?,
                  @ColumnInfo(name = "album") val album: String?,
                  @ColumnInfo(name = "releaseDate") val releaseDate: String?,
                  @ColumnInfo(name = "rating") val rating: Int?,
                  @ColumnInfo(name = "genre") val genre: String?,
                  @ColumnInfo(name = "isPreOrdered") val isPreordered: Int?) {
}