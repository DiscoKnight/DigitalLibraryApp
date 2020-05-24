package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        createGameDatabase()
        createMovieDatabase()
        createMusicDatabase()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return true
    }

    private fun createMusicDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            MusicDB::class.java, "musicDB"
        ).build()
    }

    private fun createGameDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            GameDB::class.java, "gameDB"
        ).build()
    }

    private fun createMovieDatabase(){
        val db = Room.databaseBuilder(
            applicationContext,
            MovieDB::class.java, "movieDB"
        ).build()
    }

}
