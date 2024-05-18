package com.example.submisionawal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submisionawal.data.response.FollowerResponseItem
import com.example.submisionawal.databinding.UserListBinding

class FollowerAdapter : ListAdapter<FollowerResponseItem, FollowerAdapter.MyViewHolder>(DIFF_CALLBACK) {

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

        fun bind(user: FollowerResponseItem) {
            binding.textView2.text = user.login
            Glide.with(binding.root.context).load(user.avatarUrl).into(binding.imageView)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowerResponseItem>() {
            override fun areItemsTheSame(oldItem: FollowerResponseItem, newItem: FollowerResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FollowerResponseItem, newItem: FollowerResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}