package com.gamecampanion.org.digitallibraryapp.Database.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieEntity(@PrimaryKey val id: Int,
                  @ColumnInfo(name = "movieTitle") val movieTitle: String?) {
}