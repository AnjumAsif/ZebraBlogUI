package com.wm.zebrablogui.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.wm.zebrablogui.R
import com.wm.zebrablogui.utility.Constant
import com.wm.zebrablogui.utility.SharedPreference

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut = 3000L
    lateinit var sharedPreferences: SharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = SharedPreference(this)
        Handler().postDelayed({
            /*This method will be executed once the timer is over
             Start your app main activity*/
            if (!sharedPreferences.getValueString(Constant.LOGIN_SUCCESS).equals("1")) {
                startActivity(Intent(this, LoginActivity::class.java))
                /* close this activity */
                finish()
            }else{
                // close this activity
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }, splashTimeOut)
    }
}
