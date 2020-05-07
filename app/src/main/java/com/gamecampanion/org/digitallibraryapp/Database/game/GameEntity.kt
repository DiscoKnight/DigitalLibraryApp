package com.gamecampanion.org.digitallibraryapp.Database.game

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(@PrimaryKey val id: Int,
                      @ColumnInfo(name = "gameName") val gameName: String?,
                      @ColumnInfo(name = "platform") val platform: String?)