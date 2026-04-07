package com.example.androidproject

import android.content.*
import android.database.sqlite.*

class MyDB(context: Context) : SQLiteOpenHelper(context, "SachDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE thongtinsach(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ten TEXT,
                tacgia TEXT,
                theloai TEXT
            )
        """
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS thongtinsach")
        onCreate(db)
    }

    fun insertSach(s: Sach) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("ten", s.ten)
        values.put("tacgia", s.tacGia)
        values.put("theloai", s.theLoai)
        db.insert("thongtinsach", null, values)
    }

    fun getAll(): ArrayList<Sach> {
        val list = ArrayList<Sach>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM thongtinsach", null)

        while (cursor.moveToNext()) {
            list.add(
                Sach(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
            )
        }
        return list
    }

    fun deleteSach(id: Int) {
        writableDatabase.delete("thongtinsach", "id=?", arrayOf(id.toString()))
    }

    fun updateSach(s: Sach) {
        val values = ContentValues()
        values.put("ten", s.ten)
        values.put("tacgia", s.tacGia)
        values.put("theloai", s.theLoai)

        writableDatabase.update("thongtinsach", values, "id=?", arrayOf(s.id.toString()))
    }
}