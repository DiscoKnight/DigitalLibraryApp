package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class ViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewcollectionlayout)
        setSupportActionBar(toolbar)

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
            R.id.action_settings -> true
            R.id.addMenuItem -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    fun onClick(view: View){
//        println("smurf1")
//
//        var imageSwitcher = view.findViewById<ImageSwitcher>(R.id.imageswitcher)
//
//        imageSwitcher.setOn
//
//        imageSwitcher.animate().scaleX(1.0f).start()
//    }
}