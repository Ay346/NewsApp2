package com.example.newsapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.model.Source
import com.example.newsapp.databinding.RowNewsArticleBinding
import com.example.newsapp.databinding.RowSorceBinding
import com.example.newsapp.ui.base.BaseAdapter
import com.example.newsapp.ui.base.BaseDiffCallback
import com.squareup.picasso.Picasso

internal class SourceAdapter(
    private val clickAction : (Source,View)->(Unit)) :
    BaseAdapter<Source,RowSorceBinding>(itemAction = clickAction) {

//    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var idTextView: TextView = view.findViewById(R.id.sourceId)
//        var nameTextView: TextView = view.findViewById(R.id.sourceName)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row_sorce, parent, false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val item = itemsList[position]
//        holder.idTextView.text = "id= "+item.id
//        holder.nameTextView.text = "name= "+item.name
//        holder.itemView.setOnClickListener{clickAction(item)}
//    }
//
//    override fun getItemCount(): Int {
//        return itemsList.size
//    }
    class DiffCallBack: BaseDiffCallback<NewsArticle>(){
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem.title==newItem.title
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): RowSorceBinding {
        return RowSorceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: RowSorceBinding, position: Int) {
       binding.sourceId.text=getItem(position).id
        binding.sourceName.text=getItem(position).name
    }

}