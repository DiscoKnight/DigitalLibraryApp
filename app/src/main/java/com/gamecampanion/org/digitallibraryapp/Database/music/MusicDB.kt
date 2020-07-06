package com.gamecampanion.org.digitallibraryapp.Database.music

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gamecampanion.org.digitallibraryapp.digitallibrary.music.MusicDao

@Database(entities = arrayOf(MusicEntity::class), version = 1)
abstract class MusicDB : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}