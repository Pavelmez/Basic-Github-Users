package com.example.submisionawal.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submisionawal.data.response.ItemsItem
import com.example.submisionawal.databinding.UserListBinding
import com.example.submisionawal.ui.detail.DetailActivity

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class MyViewHolder(private val binding: UserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ItemsItem) {
            binding.textView2.text = user.login
            Glide.with(binding.root.context).load(user.avatarUrl).into(binding.imageView)
            binding.root.setOnClickListener {
                val intentDetail = Intent(binding.root.context, DetailActivity::class.java).apply {
                    putExtra("user", user)
                }
                binding.root.context.startActivity(intentDetail)
            }
        }
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