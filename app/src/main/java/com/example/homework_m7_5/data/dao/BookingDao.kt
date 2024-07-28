package com.example.homework_m7_5.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework_m7_5.data.model.Booking

@Dao
interface BookingDao {
    @Query("SELECT * FROM booking WHERE roomId = :roomId")
    fun getBookingsForRoom(roomId: Int): LiveData<List<Booking>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: Booking)

    @Delete
    suspend fun deleteBooking(booking: Booking)
}