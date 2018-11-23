package com.example.guldana.myhotel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guldana.myhotel.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    val mAuth = FirebaseAuth.getInstance()

    val mDatabase = FirebaseDatabase.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val mDatabaseReference = mDatabase.reference.child("users")
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference.child(mUser!!.uid)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val greeting = "Welcome " + snapshot.child("name").value as String + " :)"
                txt_hello.text = greeting
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return view
    }
}
