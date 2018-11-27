package com.example.guldana.myhotel

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guldana.myhotel.Model.Booking
import kotlinx.android.synthetic.main.booking_list_item.view.*

class BookingsListAdapter(private val bookings: ArrayList<Booking>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        p0.itemView.txt_booking_room.text = bookings[p1].roomTitle
        p0.itemView.txt_booking_user.text = bookings[p1].userName
        val bookingDate = bookings[p1].dayOfMonth + "/" + bookings[p1].monthOfYear + "/" + bookings[p1].year
        p0.itemView.txt_booking_date.text = bookingDate
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return BookingsViewHolder(LayoutInflater.from(p0.context)
                .inflate(R.layout.booking_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return bookings.size
    }

    class BookingsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)
}