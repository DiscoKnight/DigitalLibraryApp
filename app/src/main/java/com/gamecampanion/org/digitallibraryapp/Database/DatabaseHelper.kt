package com.gamecampanion.org.digitallibraryapp.Database

import android.content.Context
import android.widget.DatePicker
import android.widget.RatingBar
import androidx.room.CoroutinesRoom
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieEntity
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity
import com.gamecampanion.org.digitallibraryapp.corountines.game.CollectionCallableGame_Insert
import com.gamecampanion.org.digitallibraryapp.corountines.movie.CollectionCallableMovie
import com.gamecampanion.org.digitallibraryapp.corountines.music.CollectionCallableMusic
import kotlinx.coroutines.*

class DatabaseHelper(context: Context) {

    var context: Context = context
    var gameList: List<GameEntity> = ArrayList()

    fun createItemGame(
        nameEditText: String,
        platform: String,
        datePicker: DatePicker,
        ratingBar: RatingBar,
        genre: String,
        isPreOrdered: Boolean,
        urlText: String
    ): GameEntity {
        return GameEntity(
            0,
            nameEditText,
            platform,
            datePicker.month.toString() + "/" + datePicker.dayOfMonth + "/" + datePicker.year,
            ratingBar.numStars,
            genre,
            true,
            urlText
        )

    }

    fun deleteItemGame(game: GameEntity) {
        runBlocking {
            GlobalScope.launch {
               createGameDatabase().gameDao().deleteGame(game)
            }
        }
    }

    fun getGamesFromDB(): List<GameEntity> {

        runBlocking {
            val v = GlobalScope.async {
                gameList = createGameDatabase().gameDao().getAllGames()
            }

            v.await()
        }

        return gameList

    }

    /////////////////////////////////////////////////////////////

    fun runGameDBInsert(entity: GameEntity): Job {
        return runBlocking {
            GlobalScope.launch() {
                CoroutinesRoom.execute(
                    createGameDatabase(),
                    true,
                    CollectionCallableGame_Insert(
                        createGameDatabase(),
                        entity
                    )
                )
            }
        }

    }

    fun runMusicDBInsert(entity: MusicEntity): Job {
        return runBlocking {
            GlobalScope.launch() {
                CoroutinesRoom.execute(
                    createMusicDatabase(),
                    true,
                    CollectionCallableMusic(
                        createMusicDatabase(),
                        entity
                    )
                )
            }
        }
    }

    fun runMovieDBInsert(entity: MovieEntity): Job {
        return runBlocking {
            GlobalScope.launch() {
                CoroutinesRoom.execute(
                    createMovieDatabase(),
                    true,
                    CollectionCallableMovie(
                        createMovieDatabase(),
                        entity
                    )
                )
            }
        }
    }

    ////////////////////////////////////////////////////////////////////

    private fun createMusicDatabase(): MusicDB {
        return Room.databaseBuilder(
            context,
            MusicDB::class.java, "musicDB"
        ).build()
    }

    fun createGameDatabase(): GameDB {
        return Room.databaseBuilder(
            context,
            GameDB::class.java,
            "gameDB"
        ).addMigrations(MIGRATION_1_2).build()
    }

    private fun createMovieDatabase(): MovieDB {
        return Room.databaseBuilder(
            context,
            MovieDB::class.java, "movieDB"
        ).build()
    }

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE GameEntity ADD COLUMN urlString TEXT")
        }
    }
}