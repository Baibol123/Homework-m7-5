package com.example.homework_m7_5.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object RoomDataInitializer {

    fun populateDatabase(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = AppDatabase.getDatabase(context)
            val roomDao = db.roomDao()

            val existingRooms = withContext(Dispatchers.IO) {
                roomDao.getAllRooms().value
            }

            if (existingRooms.isNullOrEmpty()) {
                val initialRooms = RoomObject.getRooms()
                roomDao.insertRooms(initialRooms)
            }
        }
    }
}