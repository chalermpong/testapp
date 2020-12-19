package com.chalermpong.testapp

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class MyTextView @JvmOverloads constructor(
            context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

        override fun onAttachedToWindow() {
            super.onAttachedToWindow()
            Log.d("MyTextView", "MyTextView attach ${this.hashCode()}")
        }

        override fun onDetachedFromWindow() {
            super.onDetachedFromWindow()
            Log.d("MyTextView", "MyTextView detach ${this.isLaidOut} ${this.hashCode()}")
        }

        override fun layout(l: Int, t: Int, r: Int, b: Int) {
            super.layout(l, t, r, b)
            Log.d("MyTextView", "MyTextView layout ${this.hashCode()}")
        }
    }
}