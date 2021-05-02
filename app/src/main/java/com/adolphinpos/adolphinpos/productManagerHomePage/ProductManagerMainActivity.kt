package com.adolphinpos.adolphinpos.productManagerHomePage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    val s_intentFilter : IntentFilter = IntentFilter()


    private lateinit var appBarConfiguration: AppBarConfiguration
    val sdf = SimpleDateFormat("hh:mm a")
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
        navigationView.setNavigationItemSelectedListener(this)
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

            }else if(tabId == R.id.products){

            }else if(tabId == R.id.Restaurant){
           findNavController(R.id.nav_host_fragment).navigate(R.id.Restaurant)

            }else if(tabId == R.id.hint){

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