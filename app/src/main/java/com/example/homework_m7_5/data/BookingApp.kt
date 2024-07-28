package com.example.homework_m7_5.data

import android.app.Application

class BookingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RoomDataInitializer.populateDatabase(this)
    }
}