package app.giaotieptienghan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.widget.ListView
import app.giaotieptienghan.model.MenuItem
import app.giaotieptienghan.adapter.DrawerAdapter


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
        val leftDrawer = findViewById<ListView>(R.id.left_drawer)

        // init menu
        var menuItems = MenuItem()
        menuItems.name = "Yêu Thích"
        menuItems.image = R.drawable.ic_love4
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Tìm Kiếm"
        menuItems.image = R.drawable.ic_search_3
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Chủ Đề Khác"
        menuItems.image = R.drawable.ic_heart203
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Quiz"
        menuItems.image = R.drawable.ic_action_dictionary
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Ngữ Pháp"
        menuItems.image = R.drawable.ic_comments16
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Nâng Cao"
        menuItems.image = R.drawable.ic_world8
        this.listMenuItem.add(menuItems)
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
        menuItems = MenuItem()
        menuItems.name = "Notification"
        menuItems.image = R.drawable.ic_notifications_white_48dp
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Privacy Policy"
        menuItems.image = R.drawable.ic_verified_user_white_24dp
        this.listMenuItem.add(menuItems)
        leftDrawer.adapter = DrawerAdapter(this, this.listMenuItem)

        // home screen
//        if (savedInstanceState == null) {
            this.homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit()
//        }
    }
}
