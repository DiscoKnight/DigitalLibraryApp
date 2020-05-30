package com.gamecampanion.org.digitallibraryapp.Database

import android.content.Context
import android.widget.DatePicker
import android.widget.RatingBar
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieEntity
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallableGame
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallableMovie
import com.gamecampanion.org.digitallibraryapp.corountines.CollectionCallableMusic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DatabaseHelper(context: Context) {

    var context: Context = context

    fun addItemGame(nameEditText : String,
                    platform: String,
                    datePicker: DatePicker,
                    ratingBar: RatingBar,
                    genre: String,
                    isPreOrdered: Boolean,
                    urlText: String): GameEntity{
        return GameEntity(0,
            nameEditText,
            platform,
            datePicker.month.toString() + "/" + datePicker.month + "/" + datePicker.year,
            ratingBar.numStars,
            genre,
            true,
            urlText)

    }

    fun addItemMusic(){}

    fun addItemMovie(){}

    fun runGameDBInsert(entity: GameEntity) : Job {
        return runBlocking {
            GlobalScope.launch(){
                CoroutinesRoom.execute(createGameDatabase(context),
                    true,
                    CollectionCallableGame(createGameDatabase(context),
                        entity))
            }
        }

    }

    fun runMusicDBInsert(entity: MusicEntity) : Job {
        return runBlocking {
            GlobalScope.launch(){
                CoroutinesRoom.execute(createMusicDatabase(context), true, CollectionCallableMusic(createMusicDatabase(context), entity))
            }
        }
    }

    fun runMovieDBInsert(entity: MovieEntity) : Job {
        return runBlocking {
            GlobalScope.launch(){
                CoroutinesRoom.execute(createMovieDatabase(context), true, CollectionCallableMovie(createMovieDatabase(context), entity))
            }
        }
    }

    ////////////////////////////////////////////////////////////////

    private fun createMusicDatabase(context: Context): MusicDB {
        return Room.databaseBuilder(
            context,
            MusicDB::class.java, "musicDB"
        ).build()
    }

    private fun createGameDatabase(context: Context) : GameDB {
        return Room.databaseBuilder(
            context,
            GameDB::class.java, "gameDB"
        ).build()
    }

    private fun createMovieDatabase(context: Context) : MovieDB {
        return Room.databaseBuilder(
            context,
            MovieDB::class.java, "movieDB"
        ).build()
    }
}