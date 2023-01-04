package com.example.carimahasiswa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterSearch(private val list: ArrayList<ItemsViewModelSearch>) : RecyclerView.Adapter<CustomAdapterSearch.ViewHolder>() {
    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val dataMahasiswaTextView: TextView = ItemView.findViewById(R.id.dataMahasiswaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_mahasiswa, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = list[position]

        holder.dataMahasiswaTextView.text = itemsViewModel.getCama
    }

    override fun getItemCount(): Int {
        return list.size
    }
}