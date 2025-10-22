package com.haredultra.greenbookhaven

import android.app.Application
import com.haredultra.greenbookhaven.data.BookDataManager

class BookHavenApplication : Application() {

    lateinit var bookDataManager: BookDataManager

    override fun onCreate() {
        super.onCreate()
        bookDataManager = BookDataManager(this)
        bookDataManager.prePopulateData()
    }
}