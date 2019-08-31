package com.wm.zebrablogui.utility

import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.wm.zebrablogui.R
import com.wm.zebrablogui.ui.activity.HomeActivity

class Utils {
    companion object {
        //show short toast message
        fun showToast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        //set custom title on support toolbar
        fun setDefaultTitleCenter(
            actionBar: ActionBar?,
            pageTitle:String,
            homeActivity: Activity
        ) {
            val d = ContextCompat.getDrawable(homeActivity,R.drawable.ic_gradient_top_header)
            val viewActionBar = homeActivity.layoutInflater.inflate(R.layout.actionbar_title_text_layout, null)
            val params = ActionBar.LayoutParams(//Center the text view in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER)
            actionBar?.setBackgroundDrawable(d)
            val textViewTitle = viewActionBar.findViewById(R.id.actionbar_textView) as TextView
            textViewTitle.text = pageTitle
            actionBar?.setCustomView(viewActionBar, params)
            actionBar?.setDisplayShowCustomEnabled(true)
            actionBar?.setDisplayShowTitleEnabled(false)
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.setIcon(android.R.color.transparent)
            actionBar?.setHomeButtonEnabled(false)
        }

        /*set text with multi color*/
        fun stTextWithSpannableString(
            context: Context,
            textView: TextView,
            sourceString: String,
            targetString: String
        ) {
            textView.setText(sourceString, TextView.BufferType.SPANNABLE)
            val span = textView.text as Spannable
            span.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.md_yellow_A700)),
                0,
                targetString.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
    }
}