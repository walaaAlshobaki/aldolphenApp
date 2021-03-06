package com.adolphinpos.adolphinpos.productManagerHomePage

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
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
import com.google.android.material.navigation.NavigationView
import com.roughike.bottombar.BottomBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.app_bar_main.*
import java.text.SimpleDateFormat
import java.util.*


class ProductManagerMainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {




    private lateinit var appBarConfiguration: AppBarConfiguration
    val sdf = SimpleDateFormat("hh:mm a")
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_product_manager_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)


        val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home,
                        R.id.nav_gallery,
                        R.id.transaction,
                        R.id.hold,
                        R.id.ProductFragment,
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




        navigationView.setNavigationItemSelectedListener(this)
        toggle.syncState()
        val headerLayout = navigationView.getHeaderView(0)
//
//        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar()!!.setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_logohome);
        toggle.toolbarNavigationClickListener = View.OnClickListener {
            val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }
        drawer .addDrawerListener(toggle)
        toggle.syncState();

//        toggle.setHomeAsUpIndicator(R.drawable.ic_logohome);

        toolbar.post {
            val d = ResourcesCompat.getDrawable(resources, R.drawable.ic_logohome, null)
            toolbar.navigationIcon = d
            toolbar.setContentInsetsAbsolute(0,0)
            toolbar.setContentInsetsRelative(0,0)
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


        val bottomBar = findViewById<View>(R.id.bottomBar) as BottomBar
        bottomBar.setOnTabSelectListener { tabId ->
            if (tabId == R.id.home) {
                findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.nav_home)

            }else if(tabId == R.id.CASH){
               findNavController(R.id.nav_host_fragment).navigate(R.id.nav_gallery)
            }else if(tabId == R.id.transaction){
                findNavController(R.id.nav_host_fragment).navigate(R.id.transaction)
            }
            else if(tabId == R.id.hold){
                 findNavController(R.id.nav_host_fragment).navigate(R.id.hold)

            }else if(tabId == R.id.ProductFragment){
                findNavController(R.id.nav_host_fragment).navigate(R.id.ProductFragment)

            }else if(tabId == R.id.Restaurant){
           findNavController(R.id.nav_host_fragment).navigate(R.id.Restaurant)

            }else if(tabId == R.id.hint){
                findNavController(R.id.nav_host_fragment).navigate(R.id.OrderPaymantFragment)
            }else if(tabId == R.id.lock){
                findNavController(R.id.nav_host_fragment).navigate(R.id.lock)

            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()

        if (id == R.id.nav_home) {
            findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.nav_home)
        } else if (id == R.id.nav_gallery) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.nav_gallery)
        } else if (id == R.id.transaction) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.transaction)
        } else if (id == R.id.hold) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.hold)
        }else if (id == R.id.ProductFragment) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.ProductFragment)
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }else if (id == R.id.Restaurant) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.Restaurant)
        }else if (id == R.id.hint) {
//            val contactIntent = Intent(applicationContext, ContactUsActivity::class.java)
//            startActivity(contactIntent)
        }else if (id == R.id.lock) {

            findNavController(R.id.nav_host_fragment).navigate(R.id.lock)
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