package com.gamecampanion.org.digitallibraryapp.Database.music

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MusicEntity(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "artist") val artist: String?) {
}