package com.alecbrando.roomdatabase.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alecbrando.roomdatabase.R
import com.alecbrando.roomdatabase.data.User

class ListAdapter(private val users : List<User>): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textId = itemView.findViewById<TextView>(R.id.id_text)
        val firstName = itemView.findViewById<TextView>(R.id.first_name)
        val lastName = itemView.findViewById<TextView>(R.id.last_name)
        val age = itemView.findViewById<TextView>(R.id.age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_row, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = users[position]
        holder.textId.text = currentItem.id.toString()
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.age.text = currentItem.age.toString()
    }

    override fun getItemCount() = users.size
}