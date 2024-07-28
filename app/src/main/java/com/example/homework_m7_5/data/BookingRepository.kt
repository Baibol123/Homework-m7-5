package com.example.homework_m7_5.data

import androidx.lifecycle.LiveData
import com.example.homework_m7_5.data.dao.BookingDao
import com.example.homework_m7_5.data.model.Booking

class BookingRepository(private val bookingDao: BookingDao) {

    fun getBookingsForRoom(roomId: Int): LiveData<List<Booking>> {
        return bookingDao.getBookingsForRoom(roomId)
    }

    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertBooking(booking)
    }

    suspend fun deleteBooking(booking: Booking) {
        bookingDao.deleteBooking(booking)
    }
}