package com.example.androidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val rcv = findViewById<RecyclerView>(R.id.rcv)
        val db = MyDB(this)

        val list = db.getAll()
        val adapter = SachAdapter(this, list)

        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
    }
}