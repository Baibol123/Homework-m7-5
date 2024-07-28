package com.example.homework_m7_5.data

import androidx.lifecycle.LiveData
import com.example.homework_m7_5.data.dao.RoomDao
import com.example.homework_m7_5.data.model.Room

class RoomRepository(private val roomDao: RoomDao) {

    fun getRooms(): LiveData<List<Room>> {
        return roomDao.getAllRooms()
    }
}