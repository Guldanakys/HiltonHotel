package com.example.guldana.myhotel.Model

class Booking (
        var id: String,
        val userName: String,
        val roomTitle: String,
        val dayOfMonth: String,
        val monthOfYear: String,
        val year: String
) {

    constructor() : this("", "", "", "", "", "") {

    }
}