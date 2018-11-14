package com.example.guldana.myhotel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener(View.OnClickListener {
            view -> login()
        })
    }

    private fun login() {
        val email = edx_email.text.toString()
        val password = edx_password.text.toString()

        if(!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if(task.isSuccessful){
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Succeessfully logged in!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "Please fill up the credentials!", Toast.LENGTH_LONG).show()
        }


        /*
        indi@mail.ru 123hello
        dan@gmail.com 456good
        https://www.youtube.com/watch?v=td-6oOlRMOU&t=148s
         */
    }
}
