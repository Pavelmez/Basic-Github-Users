package com.example.submisionawal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submisionawal.R
import com.example.submisionawal.data.response.ItemsItem

class FavoriteAdapter(private val onItemClick: (ItemsItem) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var items: List<ItemsItem> = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.findViewById(R.id.imageView)
        val usernameTextView: TextView = itemView.findViewById(R.id.textView2)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(items[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(holder.itemView)
            .load(item.avatarUrl)
            .into(holder.avatarImageView)

        holder.usernameTextView.text = item.login
    }

    override fun getItemCount(): Int = items.size

    fun submitList(items: List<ItemsItem>) {
        this.items = items
        notifyDataSetChanged()
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

