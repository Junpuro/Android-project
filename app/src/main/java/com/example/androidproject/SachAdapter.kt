package com.example.androidproject

import android.content.*
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class SachAdapter(val context: Context, val list: ArrayList<Sach>) :
    RecyclerView.Adapter<SachAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tv = v.findViewById<TextView>(R.id.tvItem)
        val btDelete = v.findViewById<Button>(R.id.btDelete)
        val btEdit = v.findViewById<Button>(R.id.btEdit)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_sach, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(h: ViewHolder, i: Int) {
        val s = list[i]
        h.tv.text = "${s.ten} - ${s.tacGia} - ${s.theLoai}"

        val db = MyDB(context)

        h.btDelete.setOnClickListener {
            db.deleteSach(s.id)
            list.removeAt(i)
            notifyDataSetChanged()
        }

        h.btEdit.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", s.id)
            intent.putExtra("ten", s.ten)
            intent.putExtra("tacgia", s.tacGia)
            intent.putExtra("theloai", s.theLoai)
            context.startActivity(intent)
        }
    }
}