package com.icm.dateroandorid

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icm.dateroandorid.models.Post

class ItemAdapter(private val context: Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val dataset: ArrayList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = dataset[position]
        holder.nombreTextView.text = post.title
        Log.d("ItemAdapter", "Binding post: ${post.title}")
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun adicionarListaPost(listaPost: ArrayList<Post>) {
        dataset.addAll(listaPost)
        notifyDataSetChanged()
        Log.d("ItemAdapter", "Data set updated with ${listaPost.size} posts")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
    }
}
