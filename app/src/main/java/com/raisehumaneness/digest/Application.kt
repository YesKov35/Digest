package com.raisehumaneness.digest

import android.app.Application
import com.raisehumaneness.digest.data.Prefs

class Application: Application() {

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)
    }
}