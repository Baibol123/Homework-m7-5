package com.example.homework_m7_5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_m7_5.R
import com.example.homework_m7_5.databinding.FragmentBookingListBinding


class BookingListFragment : Fragment() {

    private var _binding: FragmentBookingListBinding? = null
    private val binding get() = _binding!!

    private val roomViewModel: BookingViewModel by viewModels()
    private lateinit var roomAdapter: RoomListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        roomAdapter = RoomListAdapter(emptyList()) { room ->
            navigateToRoomDetail(room.id)
        }

        binding.rvRooms.apply {
            adapter = roomAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        roomViewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            roomAdapter.updateRooms(rooms)
        }
    }

    private fun navigateToRoomDetail(roomId: Int) {
        val action = BookingListFragmentDirections.actionRoomListFragmentToRoomDetailFragment(roomId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}