package com.example.homework_m7_5.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.homework_m7_5.data.AppDatabase
import com.example.homework_m7_5.data.entities.BookingEntity
import com.example.homework_m7_5.data.BookingRepository
import com.example.homework_m7_5.data.RoomRepository
import com.example.homework_m7_5.data.model.Booking
import com.example.homework_m7_5.data.model.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class BookingViewModel(application: Application) : AndroidViewModel(application) {

    private val bookingRepository: BookingRepository
    private val roomRepository: RoomRepository

    val rooms: LiveData<List<Room>>

    private val _bookingStatus = MutableLiveData<Boolean>()
    val bookingStatus: LiveData<Boolean> get() = _bookingStatus

    private val _bookingDate = MutableLiveData<Date?>()
    val bookingDate: LiveData<Date?> get() = _bookingDate

    init {
        val bookingDao = AppDatabase.getDatabase(application).bookingDao()
        val roomDao = AppDatabase.getDatabase(application).roomDao()
        bookingRepository = BookingRepository(bookingDao)
        roomRepository = RoomRepository(roomDao)
        rooms = roomRepository.getRooms()
    }

    fun getBookingsForRoom(roomId: Int): LiveData<List<Booking>> {
        return bookingRepository.getBookingsForRoom(roomId)
    }

    fun insertBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRepository.insertBooking(booking)
            updateBookingStatus(true, Date(booking.date))
        }
    }

    fun deleteBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRepository.deleteBooking(booking)
            updateBookingStatus(false, null)
        }
    }

    private fun updateBookingStatus(status: Boolean, date: Date?) {
        _bookingStatus.postValue(status)
        _bookingDate.postValue(date)
    }
}