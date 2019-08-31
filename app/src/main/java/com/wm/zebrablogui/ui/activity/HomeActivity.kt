package com.wm.zebrablogui.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.wm.zebrablogui.R
import com.wm.zebrablogui.adapter.HomeListData
import com.wm.zebrablogui.adapter.ImageModel
import com.wm.zebrablogui.adapter.SlidingImageAdapter
import com.wm.zebrablogui.utility.Constant
import com.wm.zebrablogui.utility.OnItemClickListener
import com.wm.zebrablogui.utility.SharedPreference
import com.wm.zebrablogui.utility.Utils
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.layout_delete_alert.view.*
import kotlinx.android.synthetic.main.nav_header_home.*
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    lateinit var sharedPreference: SharedPreference
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewLogout -> {
                logoutAlertDialog()
            }
            R.id.textViewUserProfile -> {



//                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.textViewArtEntertainment -> {
                startActivity(Intent(this, RegistrationActivity::class.java))
            }
        }
    }

    /*for image slider*/
    private var imageModelArrayList: ArrayList<ImageModel>? = null
    private val myImageList = intArrayOf(
        R.drawable.ic_1,
        R.drawable.ic_2,
        R.drawable.ic_3,
        R.drawable.ic_4,
        R.drawable.ic_5,
        R.drawable.ic_6,
        R.drawable.ic_7
    )
    /*static field here*/
    companion object {
        private var currentPage = 0
        private var NUM_PAGES = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*shared preference initialization*/
        sharedPreference = SharedPreference(this)
        /*set action bar title in center*/
        val actionBar = supportActionBar
        Utils.setDefaultTitleCenter(actionBar, "Zebra", this)

        /*show pager indicator*/
        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()
        initForSlider()

        /*load recycler data*/
        recyclerHome.layoutManager = LinearLayoutManager(this)
        recyclerHome.setHasFixedSize(true)
        recyclerHome.isNestedScrollingEnabled = false
        val messageListAdapter = HomeListData(this, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@HomeActivity, BlogDetailsActivity::class.java))
            }
        })
        recyclerHome.adapter = messageListAdapter

        /*set drawer layout*/
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        drawerLayout.setScrimColor(Color.TRANSPARENT)
        val toggle = object : ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            private val scaleFactor = 6f
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val slideX = drawerView.width * slideOffset
                parentConstraint.translationX = slideX
                parentConstraint.scaleX = 1 - slideOffset / scaleFactor
                parentConstraint.scaleY = 1 - slideOffset / scaleFactor
//                parentConstraint.translationX = slideX
            }
        }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        textViewLogout.setOnClickListener(this)
        textViewUserProfile.setOnClickListener(this)
        textViewArtEntertainment.setOnClickListener(this)

    }

    private fun initForSlider() {
        view_pager!!.adapter = SlidingImageAdapter(this@HomeActivity, this.imageModelArrayList!!)
        indicator.setViewPager(view_pager)
        val density = resources.displayMetrics.density
        /* Set circle indicator radius */
        indicator.radius = 5 * density
        NUM_PAGES = imageModelArrayList!!.size

        /* Auto start of viewpager */
        val handler = Handler()
        val update = Runnable {
            if (currentPage == NUM_PAGES) currentPage = 0
            view_pager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)

        /* Pager listener over indicator */
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {
            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        /* Inflate the menu; this adds items to the action bar if it is present. */
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /*return image list*/
    private fun populateList(): ArrayList<ImageModel> {
        val list = ArrayList<ImageModel>()
        for (i in 0..6) {
            val imageModel = ImageModel(
                myImageList[i],
                getString(R.string.top_msg),
                getString(R.string.published_date),
                getString(R.string.bottom_msg)
            )
            list.add(imageModel)
        }

        return list
    }

    /* delete alert */
    @SuppressLint("InflateParams")
    private fun logoutAlertDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.layout_delete_alert, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        //show dialog
        val mAlertDialog = mBuilder.show()
        mDialogView.textYes.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //logout from app
            sharedPreference.removeKey(Constant.LOGIN_SUCCESS)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        mDialogView.textCancel.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }

    }

}
