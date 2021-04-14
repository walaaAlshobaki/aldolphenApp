package com.adolphinpos.adolphinpos.productManagerHomePage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.home.ViewPruduct.ViewProductActivity
import com.google.android.material.navigation.NavigationView
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.*
import java.text.SimpleDateFormat
import java.util.*


class ProductManagerMainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    val s_intentFilter : IntentFilter = IntentFilter()


    private lateinit var appBarConfiguration: AppBarConfiguration
    val sdf = SimpleDateFormat("hh:mm a")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_manager_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        s_intentFilter.addAction(Intent.ACTION_TIME_TICK)
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        registerReceiver(m_timeChangedReceiver, s_intentFilter);

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.transaction,
                R.id.hold,
                R.id.products,
                R.id.Restaurant,
                R.id.hint,
                R.id.lock
            ), drawer
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
                val toggle = ActionBarDrawerToggle(
                    this,
                    drawer,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
                )
        toggle.setHomeAsUpIndicator(R.drawable.ic_logohome);
        drawer.addDrawerListener(toggle)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowTitleEnabled(false)
//        getSupportActionBar()!!.setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_logohome);
        toggle.syncState()
//        navigationView.setNavigationItemSelectedListener(this)
        val headerLayout = navigationView.getHeaderView(0)
        toggle.toolbarNavigationClickListener = View.OnClickListener {
            val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
//        Picasso.get().load(R.drawable.ic_time).transform(CircleTransform()).into(timeNoImage)
        currencyNoLin.setOnClickListener {
            val intent = Intent(
                this,
                CurrencyActivity::class.java
            )

           startActivity(intent)
        }
        val someHandler = Handler(mainLooper)
        someHandler.postDelayed(object : Runnable {
            override fun run() {
                timeNo.setText(SimpleDateFormat("hh:mm a", Locale.US).format(Date()))
                someHandler.postDelayed(this, 1000)
            }
        }, 10)

            userName.text= userInfo.firstName +" "+ userInfo.lastName
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home,
//                R.id.nav_gallery,
//                R.id.transaction,
//                R.id.hold,
//                R.id.products,
//                R.id.Restaurant,
//                R.id.hint,
//                R.id.lock
//            ), drawerLayout
//        )
////       setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)


            val bottomNavigationView: BottomNavigationView =
                findViewById<View>(R.id.bottomNavigation) as BottomNavigationView

            val bottomNavigationItem = BottomNavigationItem(
                "Home",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_home
            )
            val bottomNavigationItem1 = BottomNavigationItem(
                "cash in/out",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_home2
            )
            val bottomNavigationItem2 = BottomNavigationItem(
                "transaction",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_home3
            )
            val bottomNavigationItem3 = BottomNavigationItem(
                "holded orders",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_home4
            )
            val bottomNavigationItem4 = BottomNavigationItem(
                "products Man.",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_home5
            )
            val bottomNavigationItem5 = BottomNavigationItem(
                "Restaurant Man.",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_home6
            )
            val bottomNavigationItem6 = BottomNavigationItem(
                "hints",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_menu_slideshow
            )
            val bottomNavigationItem7 = BottomNavigationItem(
                "lock",
                ContextCompat.getColor(this, R.color.appMainColor),
                R.drawable.ic_menu_slideshow
            )
            bottomNavigationView.addTab(bottomNavigationItem)
            bottomNavigationView.addTab(bottomNavigationItem1)
            bottomNavigationView.addTab(bottomNavigationItem2)
            bottomNavigationView.addTab(bottomNavigationItem3)
            bottomNavigationView.addTab(bottomNavigationItem4)
            bottomNavigationView.addTab(bottomNavigationItem5)
            bottomNavigationView.addTab(bottomNavigationItem6)
            bottomNavigationView.addTab(bottomNavigationItem7)
            bottomNavigationView.activateTabletMode();
            bottomNavigationView.isColoredBackground(true);
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(R.color.red);
            bottomNavigationView.setBackgroundColor(resources.getColor(R.color.appMainColor))
        }


//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.product_manager_main, menu)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
//        R.id.nav_home,
//                R.id.nav_gallery,
//                R.id.transaction,
//                R.id.hold,
//                R.id.products,
//                R.id.Restaurant,
//                R.id.hint,
//                R.id.lock
        if (id == R.id.nav_home) {
//            val intent = Intent(
//                com.towInOnesolution.bymyeyes.ui.HomePage.currentActivity,
//                CustomAlartDialog::class.java
//            )
//            intent.putExtra("action", "language")
//            startActivity(intent)
        } else if (id == R.id.nav_gallery) {
//            val intent = Intent(
//                com.towInOnesolution.bymyeyes.ui.HomePage.currentActivity,
//                CustomAlartDialog::class.java
//            )
//            intent.putExtra("action", "logout")
//            startActivity(intent)
        } else if (id == R.id.transaction) {
//            val userId: String = PrefUtilities.with(this).getUserId()
//            val profileIntent = Intent(applicationContext, HelperProfileActivity::class.java)
//            profileIntent.putExtra("userId", userId)
//            println("userId   $userId")
//            startActivity(profileIntent)
        } else if (id == R.id.hold) {
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }else if (id == R.id.products) {
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }else if (id == R.id.Restaurant) {
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }else if (id == R.id.hint) {
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }else if (id == R.id.lock) {
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(m_timeChangedReceiver)
    }
    private val m_timeChangedReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_TIME_CHANGED || action == Intent.ACTION_TIMEZONE_CHANGED) {
                runOnUiThread {
                    timeNo.text = sdf.format(Date())
                }
            }
        }
    }
}