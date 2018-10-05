package app.giaotieptienghan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ListView
import app.giaotieptienghan.cipher.Decryption
import app.giaotieptienghan.cipher.ObfuscatedString


class MainActivity : AppCompatActivity() {
    private var f1950d: Decryption? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val mDrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val mDrawerToggle = ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        val leftDrawer = findViewById<ListView>(R.id.left_drawer)
    }
}
