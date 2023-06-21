package com.example.kotlinflowapplication.ui.articleSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinflowapplication.data.model.ArticleCollect
import com.example.kotlinflowapplication.databinding.ArticleItemBinding
import com.example.kotlinflowapplication.view.BaseListAdapter
import com.example.kotlinflowapplication.view.DiffUtilItemCallback

class ArticleSearchNormalAdapter : BaseListAdapter<ArticleCollect>(object : DiffUtilItemCallback<ArticleCollect> {
        override fun areItemsTheSame(oldItem: ArticleCollect, newItem: ArticleCollect): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleCollect, newItem: ArticleCollect): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = ArticleItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleSearchNormalViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        item.position = "${position + 1}"
        val articleSearchNormalViewHolder = holder as ArticleSearchNormalViewHolder
        articleSearchNormalViewHolder.bind(item,position)
    }

    override fun submitList(list: MutableList<ArticleCollect>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    class ArticleSearchNormalViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticleCollect?,position: Int) {
            binding.itemData = item
            binding.executePendingBindings()
        }
    }
}
