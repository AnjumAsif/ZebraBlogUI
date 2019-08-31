package com.wm.zebrablogui.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.StyleSpan
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wm.zebrablogui.R
import com.wm.zebrablogui.adapter.HomeListData
import com.wm.zebrablogui.utility.OnItemClickListener
import com.wm.zebrablogui.utility.Utils
import kotlinx.android.synthetic.main.activity_blog_details.*


class BlogDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_details)
        /*set action bar title in center*/
        val actionBar = supportActionBar
        Utils.setDefaultTitleCenter(actionBar, "Zebra", this@BlogDetailsActivity)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        /*set ui data*/
        textViewContainer.setText(setBodyMessage(), TextView.BufferType.SPANNABLE)
        /*load recycler data*/
        recyclerDetails.layoutManager = LinearLayoutManager(this)
        recyclerDetails.setHasFixedSize(true)
        recyclerDetails.isNestedScrollingEnabled = false
        val messageListAdapter = HomeListData(this, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                startActivity(Intent(this@BlogDetailsActivity, BlogDetailsActivity::class.java))
            }
        })
        recyclerDetails.adapter = messageListAdapter

    }

    private fun setBodyMessage(): SpannableStringBuilder {
        val completeString = StringBuilder()
        completeString.append(getString(R.string.body_msg_1))
        completeString.append(getString(R.string.body_msg_2))
        completeString.append(getString(R.string.body_msg_3))
        completeString.append(getString(R.string.body_msg_4))
        completeString.append(getString(R.string.body_msg_5))
        completeString.append(getString(R.string.body_msg_6))
//        completeString.append(getString(R.string.body_msg_7))
        //build the spannable String
        val spannableString = SpannableStringBuilder(completeString.toString())
        spannableString.setSpan(
            StyleSpan(Typeface.ITALIC),
            completeString.indexOf(getString(R.string.body_msg_1)),
            completeString.indexOf(getString(R.string.body_msg_1)).plus(getString(R.string.body_msg_1).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.DKGRAY),
            completeString.indexOf(getString(R.string.body_msg_1)),
            completeString.indexOf(getString(R.string.body_msg_1)).plus(getString(R.string.body_msg_1).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            completeString.indexOf(getString(R.string.body_msg_2)),
            completeString.indexOf(getString(R.string.body_msg_2)).plus(getString(R.string.body_msg_2).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.NORMAL),
            completeString.indexOf(getString(R.string.body_msg_3)),
            completeString.indexOf(getString(R.string.body_msg_3)).plus(getString(R.string.body_msg_3).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            StyleSpan(Typeface.NORMAL),
            completeString.indexOf(getString(R.string.body_msg_4)),
            completeString.indexOf(getString(R.string.body_msg_4)).plus(getString(R.string.body_msg_4).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            completeString.indexOf(getString(R.string.body_msg_5)),
            completeString.indexOf(getString(R.string.body_msg_5)).plus(getString(R.string.body_msg_5).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.NORMAL),
            completeString.indexOf(getString(R.string.body_msg_6)),
            completeString.indexOf(getString(R.string.body_msg_6)).plus(getString(R.string.body_msg_6).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        /*we can set image in text view*/
        /*
        spannableString.setSpan(
            ImageSpan(
                this, R.drawable.ic_format_quote
            ),
            spannableString.length - getString(R.string.body_msg_7).length,
            spannableString.length - getString(R.string.body_msg_7).length + 1, 0
        )

        spannableString.setSpan(
            StyleSpan(Typeface.ITALIC),
            completeString.indexOf(getString(R.string.body_msg_7)),
            completeString.indexOf(getString(R.string.body_msg_7)).plus(getString(R.string.body_msg_7).length),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )*/

        return spannableString

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
