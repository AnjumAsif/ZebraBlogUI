package com.wm.zebrablogui.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wm.zebrablogui.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSignIn.setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
        }
    }
}
