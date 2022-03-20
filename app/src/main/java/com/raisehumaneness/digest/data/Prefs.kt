package com.raisehumaneness.digest.data

import android.content.Context
import com.raisehumaneness.digest.utils.Constants


class Prefs(context: Context) {

    private val sp = context.getSharedPreferences("raise_humaneness_digest", Context.MODE_PRIVATE)

    fun setCountry(country: Int) {
        val editor = sp.edit()
        editor.putInt(Constants.PREFS_COUNTRY, country)
        editor.apply()
    }

    fun getCountry(): Int {
        return sp.getInt(Constants.PREFS_COUNTRY, -1)
    }

    fun setHelp(help: Boolean) {
        val editor = sp.edit()
        editor.putBoolean(Constants.PREFS_HELP, help)
        editor.apply()
    }

    fun getHelp(): Boolean {
        return sp.getBoolean(Constants.PREFS_HELP, true)
    }
}