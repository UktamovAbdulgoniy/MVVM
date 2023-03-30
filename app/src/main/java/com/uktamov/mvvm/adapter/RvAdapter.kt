package com.uktamov.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uktamov.mvvm.databinding.ImageLayoutBinding
import com.uktamov.mvvm.model.ImageResponseItem

class RvAdapter : ListAdapter<ImageResponseItem, RvAdapter.RvViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<ImageResponseItem>() {
        override fun areItemsTheSame(oldItem: ImageResponseItem, newItem: ImageResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageResponseItem, newItem: ImageResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class RvViewHolder(private val binding: ImageLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageResponseItem) {
            with(binding) {
                Glide.with(userImage)
                    .load(item.user.links.toString())
                    .circleCrop()
                    .into(userImage)

                userName.text = item.user.name
                Glide.with(imageView)
                    .load(item.urls.regular)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(ImageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}