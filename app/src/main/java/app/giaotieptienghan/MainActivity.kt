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
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val listMenuItem: ArrayList<MenuItem> = ArrayList()
    lateinit var homeFragment: HomeFragment
    private var mInterstitialAd: InterstitialAd? = null
    private var isAdShown: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mInterstitialAd = InterstitialAd(this)
        if (BuildConfig.DEBUG) {
            mInterstitialAd!!.adUnitId = getString(R.string.ad_popup_demo_id)
            mInterstitialAd!!.loadAd(AdRequest.Builder().addTestDevice("D9E46D2AE14D4064F48C60B07D4218FC").build())
        } else {
            mInterstitialAd!!.adUnitId = getString(R.string.ad_popup_1)
            mInterstitialAd!!.loadAd(AdRequest.Builder().build())
        }
        mInterstitialAd!!.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                // Load the next interstitial.
                if (BuildConfig.DEBUG) {
                    mInterstitialAd!!.loadAd(AdRequest.Builder().addTestDevice("D9E46D2AE14D4064F48C60B07D4218FC").build())
                } else {
                    mInterstitialAd!!.loadAd(AdRequest.Builder().build())
                }
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                if (this@MainActivity.getWindow().getDecorView().isShown() && !isAdShown) {
                    isAdShown = true
                    mInterstitialAd!!.show()
                }
            }
        }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val mDrawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val mDrawerToggle = ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        // initViews menu
        var menuItems = MenuItem()
        menuItems.name = "Yêu Thích"
        menuItems.image = R.drawable.ic_love4
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Tìm Kiếm"
        menuItems.image = R.drawable.ic_search_3
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Luyện tập"
        menuItems.image = R.drawable.ic_world8
        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Game luyện nghe"
        menuItems.image = R.drawable.ic_action_dictionary
        this.listMenuItem.add(menuItems)
        if (BuildConfig.FLAVOR.contains("jp", true)) {
            menuItems = MenuItem()
            menuItems.name = "Ngữ Pháp"
            menuItems.image = R.drawable.ic_comments16
            this.listMenuItem.add(menuItems)
        }
//        menuItems = MenuItem()
//        menuItems.name = "Nâng Cao"
//        menuItems.image = R.drawable.ic_world8
//        this.listMenuItem.add(menuItems)
        menuItems = MenuItem()
        menuItems.name = "Update" //faked
        menuItems.image = R.drawable.ic_favorite
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
        menuItems.name = "Ứng dụng khác"
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
        left_drawer_list.adapter = DrawerAdapter(this, this.listMenuItem)
        left_drawer_list.onItemClickListener = onMenuItemClicked()
        // home screen
        if (savedInstanceState == null) {
            this.homeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, homeFragment).commit()
        }
    }

    private inner class onMenuItemClicked : OnItemClickListener {
        override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, j: Long) {
            this@MainActivity.selectDrawerItem(i)
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
    private fun selectDrawerItem(i: Int) {
        this.drawer_layout.closeDrawer(this.drawer)
        Handler().postDelayed(Runnable {
            try {
                var cls: Class<*>? = null
                if (i == 0) {
                    cls = FavoriteDetailActivity::class.java
                } else if (i == 1) {
                    cls = SearchActivity::class.java
                } else if (i == 2) {
                    cls = QuizActivity1::class.java
                } else if (i == 3) {
                    cls = QuizDetail::class.java
                } else if (i == 4) {
                    cls = GrammaActivity::class.java
                } else if (i == 4 + 2) {
                    this@MainActivity.openPlayStore(packageName)
                    return@Runnable
                } else if (i == 5 + 2) {
                    val intent = Intent()
                    intent.action = "android.intent.action.SEND"
                    intent.putExtra("android.intent.extra.TEXT", getString(R.string.share_app_text).replace("__PACKAGE_NAME__", packageName))
                    intent.type = "text/plain"
                    startActivity(intent)
                    return@Runnable
                } else if (i == 6 + 2) {
                    this@MainActivity.ourApp()
                    return@Runnable
                }
                startActivity(this, cls)
            } catch (unused: Exception) {
            }
        }, 200)
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
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Ngoai+Ngu+Software")))
        } catch (unused: ActivityNotFoundException) {
        }

    }

}
