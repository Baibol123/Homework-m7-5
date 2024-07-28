package com.example.homework_m7_5.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Room")
data class Room(
    @PrimaryKey val id: Int,
    val name: String,
    val imageResId: Int
)
