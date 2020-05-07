package com.gamecampanion.org.digitallibraryapp.Database.music

import androidx.room.Database
import com.gamecampanion.org.digitallibraryapp.digitallibrary.music.MusicDao

@Database(entities = arrayOf(MusicEntity::class), version = 1)
abstract class MusicDB {
    abstract fun musicDao(): MusicDao
}