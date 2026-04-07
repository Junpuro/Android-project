package com.example.androidproject

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val edTen = findViewById<EditText>(R.id.edTen)
        val edTacGia = findViewById<EditText>(R.id.edTacGia)
        val btUpdate = findViewById<Button>(R.id.btUpdate)

        val db = MyDB(this)

        val id = intent.getIntExtra("id", -1)
        val ten = intent.getStringExtra("ten")
        val tacgia = intent.getStringExtra("tacgia")

        edTen.setText(ten)
        edTacGia.setText(tacgia)

        btUpdate.setOnClickListener {
            val s = Sach(
                id,
                edTen.text.toString(),
                edTacGia.text.toString(),
                "Khác"
            )
            db.updateSach(s)
            finish()
        }
    }
}