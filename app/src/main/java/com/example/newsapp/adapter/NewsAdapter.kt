package com.example.newsapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.R
import com.example.newsapp.databinding.RowNewsArticleBinding
import com.example.newsapp.models.ArticalUi
import com.example.newsapp.ui.base.BaseAdapter
import com.example.newsapp.ui.base.BaseDiffCallback
import com.sawaf.weatherappex.tools.loadImage
import com.squareup.picasso.Picasso

class NewsAdapter(
    itemAction: (ArticalUi, View) -> Unit,
    val saveaction: (ArticalUi) -> Unit
) : BaseAdapter<ArticalUi, RowNewsArticleBinding>(itemAction = itemAction) {

    override fun createBinding(parent: ViewGroup, viewType: Int): RowNewsArticleBinding {
        return RowNewsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bind(binding: RowNewsArticleBinding, position: Int) {
        val item = getItem(position)
        val dateString = item.publishedAt
        var list = dateString!!.split("T")
        binding.newsPublishedAt.text = "${list[0]} & ${list[1]}"
        if (item.urlToImage == null) {
            binding.newsImage.loadImage(
                "https://img.youm7.com/large/202301030813411341.jpg",
                context = binding.root.context
            )
        } else {
            binding.newsImage.loadImage(
                item.urlToImage,
                context = binding.root.context
            )
        }

//        Picasso.with(binding.newsImage.context)
//            .load(item.url)
//            .into(binding.newsImage)
        binding.newsAuthor.text = item.author
        binding.newsTitle.text = item.title
        if (item.isBookMarked) {
            binding.saveBtn.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            binding.saveBtn.setImageResource(android.R.drawable.btn_star_big_off)
        }
        binding.saveBtn.setOnClickListener {
            saveaction.invoke(item)
            item.isBookMarked = true
            notifyItemChanged(position)
        }
    }

    class DiffCallBack : BaseDiffCallback<ArticalUi>() {
        override fun areItemsTheSame(oldItem: ArticalUi, newItem: ArticalUi): Boolean {
            return oldItem.title == newItem.title
        }
    }

}