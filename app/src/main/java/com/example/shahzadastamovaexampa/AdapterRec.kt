package com.example.shahzadastamovaexampa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRec (
    private val click: (contact: Contact) -> Unit
): RecyclerView.Adapter<AdapterRec.ViewHolder>() {
    private var list = mutableListOf<Contact>()

    fun setData(contact: Contact) {
        list.add(contact)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = list[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val view: View, val click: (contact: Contact) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bind(item: Contact) {

            val contact = view.findViewById<AppCompatTextView>(R.id.item_txt)
            contact.text = item.toString()

            val btnGo = view.findViewById<AppCompatButton>(R.id.item_btn)
            btnGo.setOnClickListener{
                click.invoke(item)
            }
        }

    }
}