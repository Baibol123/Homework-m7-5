package com.example.homework_m7_5.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_m7_5.data.dao.BookingDao
import com.example.homework_m7_5.data.dao.RoomDao
import com.example.homework_m7_5.data.model.Booking

@Database(entities = [Booking::class, androidx.room.Room::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
