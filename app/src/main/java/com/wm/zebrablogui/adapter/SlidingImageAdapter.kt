package com.wm.zebrablogui.adapter

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.wm.zebrablogui.R
import com.wm.zebrablogui.utility.Utils


class SlidingImageAdapter(private val context: Context, private val imageModelArrayList: ArrayList<ImageModel>) :
    PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.sliding_image_layout, view, false)!!
        val imageView = imageLayout
            .findViewById(R.id.image) as ImageView
        val textViewTopMsg = imageLayout
            .findViewById(R.id.textViewTopMsg) as TextView
        val textViewPublishDate = imageLayout
            .findViewById(R.id.textViewPublishedDate) as TextView
        val textViewBottomMsg = imageLayout
            .findViewById(R.id.textViewBottomMsg) as TextView
        imageView.setImageResource(imageModelArrayList[position].imageDrawable)
        textViewTopMsg.text = imageModelArrayList[position].topMessage
        /*set some part of text with color*/
        Utils.stTextWithSpannableString(context,textViewPublishDate,
            imageModelArrayList[position].publishedDate,"Posted on October 4, 2013 by")

        textViewBottomMsg.text = imageModelArrayList[position].bottomMsg
        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

}