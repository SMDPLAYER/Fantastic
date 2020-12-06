package com.mysoftpanda.android.onlineexam.app

import android.app.Application
import uz.smd.fantastic.data.local.LocalStorage

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
        instance = this
    }

    companion object{
        lateinit var instance : App
    }
}