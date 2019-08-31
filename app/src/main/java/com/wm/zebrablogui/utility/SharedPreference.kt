package com.wm.zebrablogui.utility

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE)
    fun save(KEY_NAME: String, status: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, status)
        editor.apply()



    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, "")

    }

    fun removeKey(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }
}