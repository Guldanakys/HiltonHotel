package com.example.guldana.myhotel

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.Toast
import com.example.guldana.myhotel.Model.HotelRoom
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hotel_room_list_item.view.*

class RoomsListAdapter(private val hotelRooms: ArrayList<HotelRoom>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {

        p0.itemView.txt_room_title.text = hotelRooms[p1].title
        p0.itemView.txt_room_type.text = hotelRooms[p1].type
        val price = "$" + hotelRooms[p1].price.toString() + "/day"
        p0.itemView.txt_room_price.text = price
        Picasso.get().load(hotelRooms[p1].imgUrl).into(p0.itemView.room_image)

        p0.itemView.txt_desc_name.setOnClickListener {
            p0.itemView.txt_room_desc.text = hotelRooms[p1].description
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return RoomsViewHolder(LayoutInflater.from(p0.context)
                .inflate(R.layout.hotel_room_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return hotelRooms.size
    }

    class RoomsViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)
}