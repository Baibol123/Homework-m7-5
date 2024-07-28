package com.example.homework_m7_5.data

import com.example.homework_m7_5.R
import com.example.homework_m7_5.data.model.Room

object RoomObject {
    fun getRooms(): List<Room> {
        return listOf(
            Room(1, "Room 1",  R.drawable.room_1),
            Room(2, "Room 2",  R.drawable.room_2),
            Room(3, "Room 3",  R.drawable.room_3),
            Room(4, "Room 4",  R.drawable.room_4),
            Room(5, "Room 5",  R.drawable.room_5),
            Room(6, "Room 6",  R.drawable.room_6),
            Room(7, "Room 7",  R.drawable.room_7),
            Room(8, "Room 8",  R.drawable.room_8),
            Room(9, "Room 9",  R.drawable.room_9),
            Room(10, "Room 10",  R.drawable.room_10)
        )
    }
}