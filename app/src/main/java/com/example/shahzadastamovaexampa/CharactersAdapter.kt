package com.example.shahzadastamovaexampa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shahzadastamovaexampa.api.Item
import com.example.shahzadastamovaexampa.api.Response
import com.example.shahzadastamovaexampa.database.OneCharacter

class CharactersAdapter(private val click: (id: Long?) -> Unit) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private var list: List<Item> = listOf()

    fun setData(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        itemView: View, private val click: (id: Long?) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {

            val name = itemView.findViewById<AppCompatTextView>(R.id.nameTxt)
            val status = itemView.findViewById<AppCompatTextView>(R.id.statusTxt)
            val location = itemView.findViewById<AppCompatTextView>(R.id.locationTxt)
            val species = itemView.findViewById<AppCompatTextView>(R.id.speciesTxt)
            val image = itemView.findViewById<ImageView>(R.id.imgIR)

            name.text = item.name
            status.text = item.status
            species.text = item.species

            Glide.with(itemView.context)
                .load(item.image)
                .into(image)

            itemView.setOnClickListener {
                click.invoke(item.id!!)
            }
        }
    }
}