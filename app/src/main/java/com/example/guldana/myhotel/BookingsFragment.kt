package com.example.guldana.myhotel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.guldana.myhotel.Model.Booking
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_bookings.view.*

class BookingsFragment : Fragment() {

    val mAuth = FirebaseAuth.getInstance()
    val mDatabase = FirebaseDatabase.getInstance()

    lateinit var refBookings: DatabaseReference

    lateinit var userName: String

    private val ADD_TASK_REQUEST = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bookings, container, false)

        val mDatabaseReference = mDatabase.reference.child("users")
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference.child(mUser!!.uid)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userName = snapshot.child("name").value as String
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        val bookingsList = ArrayList<Booking>()
        refBookings = FirebaseDatabase.getInstance().getReference("bookings")
        refBookings.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                bookingsList.clear()
                if(p0.exists()){
                    for(b in p0.children){
                        val booking = b.getValue(Booking::class.java)
                        if(booking!!.userName == userName) {
                            bookingsList.add(booking)
                        }
                    }
                }
                val adapter = BookingsListAdapter(bookingsList)
                val recyclerView = view.findViewById(R.id.recycler) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = adapter
            }
        })

        view.flt_btn_add.setOnClickListener {
            val intent = Intent(activity, AddBookingActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
            bookingsList.clear()
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(activity, "Booking completed successfully!", Toast.LENGTH_LONG).show()
            }
        }
    }

}
