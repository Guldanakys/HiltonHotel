package com.example.guldana.myhotel.Model

class News (
        val id: String,
        val title: String,
        val desc: String,
        val imgUrl:String
) {

    constructor() : this("", "", "", "") {

    }
}