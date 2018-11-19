package com.example.guldana.myhotel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mDatabase = FirebaseDatabase.getInstance().getReference("users")

        btn_register.setOnClickListener(View.OnClickListener {
            view -> register()
        })
    }

    private fun register() {

        var email = edx_email.text.toString()
        var password = edx_password.text.toString()
        var confirmPassword = edx_confirm_password.text.toString()
        var name = edx_name.text.toString()

        if(!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && !name.isEmpty()) {
            if(password.equals(confirmPassword)) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        val current_user = mAuth.currentUser
                        val current_uid = current_user!!.uid
                        val user = User(current_uid, name)
                        mDatabase.child(current_uid).setValue(user)
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Succeessfully signed in!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please fill up the credentials!", Toast.LENGTH_LONG).show()
        }

    }
}
