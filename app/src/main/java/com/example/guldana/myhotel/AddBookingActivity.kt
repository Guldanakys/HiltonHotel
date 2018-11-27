package com.example.guldana.myhotel

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.guldana.myhotel.Model.Booking
import com.example.guldana.myhotel.Model.HotelRoom
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_booking.*

class AddBookingActivity : AppCompatActivity() {

    lateinit var refRooms: DatabaseReference
    lateinit var refBookings: DatabaseReference
    val mDatabaseUsers = FirebaseDatabase.getInstance()
    val mAuth = FirebaseAuth.getInstance()
    lateinit var username: String
    lateinit var roomtitle: String
    lateinit var bDay: String
    lateinit var bMonth: String
    lateinit var bYear: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_booking)

        roomtitle = "Not working((("

        val mDatabaseReference = mDatabaseUsers.reference.child("users")
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference.child(mUser!!.uid)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                username = snapshot.child("name").value as String
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        val myOptions = ArrayList<String>()

        refRooms = FirebaseDatabase.getInstance().getReference("rooms")
        refRooms.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(n in p0.children){
                        val hotelRoom = n.getValue(HotelRoom::class.java)
                        myOptions.add(hotelRoom!!.title)
                    }
                }

                val spAdapter = ArrayAdapter(this@AddBookingActivity, android.R.layout.simple_spinner_item, myOptions)
                spAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                my_spinner.adapter = spAdapter
                my_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
                        // Display the selected item text on text view
                        roomtitle = parent.getItemAtPosition(position).toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>){

                    }
                }

            }
        })

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->

            val msg = "" + dayOfMonth + "/" + (month + 1) + "/" + year
            txt_date.text = msg
            bDay = dayOfMonth.toString()
            bMonth = (month + 1).toString()
            bYear = year.toString()
        }

        btn_save_booking.setOnClickListener {
            val booking = Booking("1", username, roomtitle, bDay, bMonth, bYear)
            saveBooking(booking)
            val result = Intent()
            setResult(Activity.RESULT_OK, result)
            finish()
        }

    }

    private fun saveBooking(booking: Booking) {
        refBookings = FirebaseDatabase.getInstance().getReference("bookings")
        val bId = refBookings.push().key!!
        booking.id = bId
        refBookings.child(bId).setValue(booking)
    }
}
