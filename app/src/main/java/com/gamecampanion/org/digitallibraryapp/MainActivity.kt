package com.gamecampanion.org.digitallibraryapp

import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.room.Room
import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        createGameDatabase()
        createMovieDatabase()
        //createMusicDatabase()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

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

        return when (item.itemId) {
            R.id.addMenuItem -> viewViewLayout()
            R.id.action_settings -> viewViewLayout()
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun viewViewLayout(): Boolean{
        Logger.getLogger("addLogger").info("debug");

        val sceneRoot: ViewGroup = findViewById(R.id.actvitymain)

        val endscene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.viewcollectionlayout, this)

        var fadeTransition: Transition = Fade()

        var transition = TransitionManager.go(endscene, fadeTransition)

        var intent = Intent()

        setContentView(R.layout.viewcollectionlayout)

        return true

    }

}
