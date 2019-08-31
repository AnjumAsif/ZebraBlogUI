package com.wm.zebrablogui.ui.activity

import android.content.Intent
import android.os.Bundle
import com.wm.onboarder.OnBoarderActivity
import com.wm.onboarder.OnboarderPage
import com.wm.zebrablogui.R
import com.wm.zebrablogui.utility.Constant
import com.wm.zebrablogui.utility.SharedPreference
import java.util.*

class IntroActivity : OnBoarderActivity() {
    lateinit var sharedPreference: SharedPreference
    override fun onFinishButtonPressed() {
        redirectToHomeScreen()
    }

    override fun onSkipButtonPressed() {
        super.onSkipButtonPressed()
       redirectToHomeScreen()
    }
    /*got to home screen*/
    private fun redirectToHomeScreen() {
        if (sharedPreference.getValueString(Constant.LOGIN_SUCCESS).equals(""))
            sharedPreference.save(Constant.LOGIN_SUCCESS, "1")
        startActivity(Intent(this, HomeActivity::class.java))
        finish()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreference = SharedPreference(this)

        val onBoarderPage1 = OnboarderPage(
            "recognizing the need is the primary condition for design".toUpperCase(),
            "Design works if it's authentic, inspired, and has a clear point of view." +
                    " it can't be a collection of point.", R.drawable.ic_done_white_24dp
        )
        val onBoarderPage2 = OnboarderPage(
            "recognizing the need is the primary condition for design".toUpperCase(),
            "Design works if it's authentic, inspired, and has a clear point of view." +
                    " it can't be a collection of point.", R.drawable.ic_done_white_24dp
        )
        val onBoarderPage3 = OnboarderPage(
            "recognizing the need is the primary condition for design".toUpperCase(),
            "Design works if it's authentic, inspired, and has a clear point of view." +
                    " it can't be a collection of point.", R.drawable.ic_done_white_24dp
        )
        /*page background color*/
        onBoarderPage1.backgroundColor = R.color.colorStart
        onBoarderPage2.backgroundColor = R.color.colorCenter
        onBoarderPage3.backgroundColor = R.color.colorStart
        /*set page title*/
        val pages = ArrayList<OnboarderPage>()
        pages.add(onBoarderPage1)
        pages.add(onBoarderPage2)
        pages.add(onBoarderPage3)
        for (page in pages) {
            page.titleColor = R.color.colorWhite
            page.descriptionColor = R.color.colorWhite
            page.isMultilineDescriptionCentered = false
        }

        setSkipButtonTitle("Skip")
        setFinishButtonTitle("Finish")

        setOnboardPagesReady(pages)
    }
}
