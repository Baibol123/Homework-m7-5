package com.example.homework_m7_5.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework_m7_5.data.model.Room

@Dao
interface RoomDao {
    @Query("SELECT * FROM Room")
    fun getAllRooms(): LiveData<List<Room>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<Room>)
}