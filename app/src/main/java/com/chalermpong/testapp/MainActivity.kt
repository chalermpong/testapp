package com.chalermpong.testapp

import android.content.*
import android.os.*
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var mService: Messenger? = null
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            mService = Messenger(service)
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if (mBound) {
                // Create and send a message to the service, using a supported 'what' value
                val msg: Message = Message.obtain(null, 1, 0, 0)
                try {
                    mService?.send(msg)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, LocalService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
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