package com.example.homework_m7_5.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_m7_5.R
import com.example.homework_m7_5.data.model.Room

class RoomListAdapter(
    private var rooms: List<Room>,
    private val onItemClick: (Room) -> Unit
) : RecyclerView.Adapter<RoomListAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)
        return RoomViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.bind(room)
    }

    fun updateRooms(newRooms: List<Room>) {
        rooms = newRooms
        notifyDataSetChanged()
    }

    inner class RoomViewHolder(
        itemView: View,
        private val onItemClick: (Room) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val roomName: TextView = itemView.findViewById(R.id.tv_room_name)
        private val roomImgView: ImageView = itemView.findViewById(R.id.iv_room)

        fun bind(room: Room) {
            roomName.text = room.name
            Glide.with(itemView.context)
                .load(room.imageResId)
                .into(roomImgView)
            itemView.setOnClickListener { onItemClick(room) }
        }
    }
}