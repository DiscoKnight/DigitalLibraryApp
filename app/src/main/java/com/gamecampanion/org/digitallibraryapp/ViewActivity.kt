package com.gamecampanion.org.digitallibraryapp

import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert.ACTION
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import kotlinx.android.synthetic.main.activity_main.*

class ViewActivity: AppCompatActivity() {

    lateinit var decector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewcollectionlayout)
        setSupportActionBar(toolbar)

        decector = GestureDetectorCompat(this, MyGestureDetector())

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

    override fun onTouchEvent(event: MotionEvent): Boolean{
        decector.onTouchEvent(event)
        return super.onTouchEvent(event)

    }
}