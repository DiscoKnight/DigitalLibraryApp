package com.gamecampanion.org.digitallibraryapp.digitallibrary.music

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity

@Dao
interface MusicDao {
    @Query("select * from MusicEntity")
    fun getAllMusic(): List<MusicEntity>

    @Insert
    fun insertMusic(music: MusicEntity)

    @Update
    fun updateMusic(music: MusicEntity)
}
