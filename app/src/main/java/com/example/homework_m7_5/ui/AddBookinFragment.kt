package com.example.homework_m7_5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.homework_m7_5.R
import com.example.homework_m7_5.data.model.Booking
import com.example.homework_m7_5.databinding.FragmentAddBookinBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddBookinFragment : Fragment() {

    private var _binding: FragmentAddBookinBinding? = null
    private val binding get() = _binding!!

    private val args: AddBookinFragmentArgs by navArgs()
    private val bookViewModel: BookingViewModel by viewModels()

    private val bookings: LiveData<List<Booking>> by lazy {
        bookViewModel.getBookingsForRoom(args.roomId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBookinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        bookings.observe(viewLifecycleOwner, Observer { bookings ->
            updateBookingStatus(bookings)
        })

        bookViewModel.bookingStatus.observe(viewLifecycleOwner, Observer { booked ->
            updateBookingStatus(if (booked) bookings.value.orEmpty() else emptyList())
        })

        bookViewModel.bookingDate.observe(viewLifecycleOwner, Observer { date ->
            binding.tvRoomBookDate.text = date?.let { formatDate(it.time) } ?: ""
        })

        bookViewModel.rooms.observe(viewLifecycleOwner, Observer { rooms ->
            rooms.find { it.id == args.roomId }?.let { room ->
                binding.ivRoom.setImageResource(room.imageResId)
                binding.tvRoomNameDetails.text = room.name
            }
        })
    }

    private fun setupClickListeners() {
        binding.btnBook.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnCancelBooking.setOnClickListener {
            cancelBooking()
        }
    }

    private fun showDatePickerDialog() {
        DatePickerDialogFragment { year, month, day ->
            val bookingDate = Calendar.getInstance().apply {
                set(year, month, day)
            }.time

            val booking = Booking(roomId = args.roomId, date = bookingDate.time)
            bookViewModel.insertBooking(booking)
            showToast("Booking successful")
        }.show(parentFragmentManager, "BookingDatePickerDialogFragment")
    }

    private fun cancelBooking() {
        bookings.value?.firstOrNull { it.roomId == args.roomId }?.let { booking ->
            bookViewModel.deleteBooking(booking)
            showToast("Booking canceled")
        } ?: showToast("No booking to cancel")
    }

    private fun updateBookingStatus(bookings: List<Booking>) {
        if (bookings.isNotEmpty()) {
            binding.tvRoomBook.text = getString(R.string.string_booked)
            binding.tvRoomBookDate.visibility = View.VISIBLE
            binding.tvRoomBookDate.text = formatDate(bookings[0].date)
        } else {
            binding.tvRoomBook.text = getString(R.string.string_available)
            binding.tvRoomBookDate.visibility = View.GONE
        }
    }

    private fun formatDate(dateInMillis: Long): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(dateInMillis))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}