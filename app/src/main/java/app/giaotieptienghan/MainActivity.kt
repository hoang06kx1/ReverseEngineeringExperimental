package app.giaotieptienghan

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import app.giaotieptienghan.model.MenuItem
import app.giaotieptienghan.adapter.DrawerAdapter
import android.content.Intent
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val listMenuItem: ArrayList<MenuItem> = ArrayList()
    lateinit var homeFragment: HomeFragment

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

        // init menu
        var menuItems = MenuItem()
        menuItems.name = "Yêu Thích"
        menuItems.image = R.drawable.ic_love4
        this.listMenuItem.add(menuItems)
//        menuItems = MenuItem()
//        menuItems.name = "Tìm Kiếm"
//        menuItems.image = R.drawable.ic_search_3
//        this.listMenuItem.add(menuItems)
//        menuItems = MenuItem()
//        menuItems.name = "Chủ Đề Khác"
//        menuItems.image = R.drawable.ic_heart203
//        this.listMenuItem.add(menuItems)
//        menuItems = MenuItem()
//        menuItems.name = "Quiz"
//        menuItems.image = R.drawable.ic_action_dictionary
//        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Ngữ Pháp"
        menuItems.image = R.drawable.ic_comments16
        this.listMenuItem.add(menuItems)
//        menuItems = MenuItem()
//        menuItems.name = "Nâng Cao"
//        menuItems.image = R.drawable.ic_world8
//        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Đánh Giá"
        menuItems.image = R.drawable.ic_favorite
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Chia Sẻ"
        menuItems.image = R.drawable.ic_action_facebook
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Our App"
        menuItems.image = R.drawable.ic_family4
        this.listMenuItem.add(menuItems)
//        menuItems = MenuItem()
//        menuItems.name = "Notification"
//        menuItems.image = R.drawable.ic_notifications_white_48dp
//        this.listMenuItem.add(menuItems)
//        menuItems = MenuItem()
//        menuItems.name = "Privacy Policy"
//        menuItems.image = R.drawable.ic_verified_user_white_24dp
//        this.listMenuItem.add(menuItems)
        left_drawer.adapter = DrawerAdapter(this, this.listMenuItem)

        // home screen
        if (savedInstanceState == null) {
            this.homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit()
        }
    }

    private inner class C0712a private constructor() : OnItemClickListener {
        override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, j: Long) {
            this@MainActivity.m16223b(i)
        }
    }

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    private fun startActivity(context: Context, cls: Class<*>?) {
        val intent = Intent(this, cls)
        intent.flags = 268435456
        startActivity(intent)
    }

    /* renamed from: b */
    private fun m16223b(i: Int) {
        this.drawer_layout.closeDrawer(this.left_drawer)
        Handler().postDelayed(Runnable {
            try {
                val mainActivity: MainActivity
                val cls: Class<*>? = null
                if (i == 0) {
//                    cls = FavoriteDetail::class.java
                } else if (i == 1) {
//                    cls = SearchActivity::class.java
                } else if (i == 2) {
                    this@MainActivity.openPlayStore(packageName)
                } else if (i == 3) {
                    this@MainActivity.ourApp()
                    return@Runnable
                }
                startActivity(this, cls)
            } catch (unused: Exception) {
        }}, 200)
    }

    private fun openPlayStore(str: String) {
        var stringBuilder: StringBuilder
        try {
            stringBuilder = StringBuilder()
            stringBuilder.append("market://details?id=")
            stringBuilder.append(str)
            startActivity(Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())))
        } catch (unused: ActivityNotFoundException) {
            stringBuilder = StringBuilder()
            stringBuilder.append("http://play.google.com/store/apps/details?id=")
            stringBuilder.append(str)
            startActivity(Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())))
        }

    }

    private fun ourApp() {
        try {
            startActivity(Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub: WingsApp Studio")))
        } catch (unused: ActivityNotFoundException) {
            startActivity(Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/search?q=pub: WingsApp Studio")))
        }

    }

}
