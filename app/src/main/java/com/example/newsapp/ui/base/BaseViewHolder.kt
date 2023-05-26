package com.example.newsapp.ui.base

import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<out T:ViewBinding>(val binding:T):RecyclerView.ViewHolder(binding.root) {
}