package com.example.androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edTen = findViewById<EditText>(R.id.edTenSach)
        val edTacGia = findViewById<EditText>(R.id.edTacGia)
        val rg = findViewById<RadioGroup>(R.id.rgTheLoai)
        val btSave = findViewById<Button>(R.id.btSave)
        val btView = findViewById<Button>(R.id.btView)

        val db = MyDB(this)

        btSave.setOnClickListener {
            val ten = edTen.text.toString()
            val tacgia = edTacGia.text.toString()

            val idChecked = rg.checkedRadioButtonId
            val theLoai = if (idChecked != -1)
                findViewById<RadioButton>(idChecked).text.toString()
            else ""

            if (ten.isEmpty() || tacgia.isEmpty() || theLoai.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val s = Sach(ten = ten, tacGia = tacgia, theLoai = theLoai)
            db.insertSach(s)

            Toast.makeText(this, "Đã lưu", Toast.LENGTH_SHORT).show()

            edTen.setText("")
            edTacGia.setText("")
            rg.clearCheck()
        }

        btView.setOnClickListener {
            val list = db.getAll()
            if (list.isEmpty()) {
                Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, ViewActivity::class.java))
            }
        }
    }
}