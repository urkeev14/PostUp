package com.example.postup.util.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postup.databinding.ItemPostBinding
import com.example.postup.model.PostEntity
import com.example.postup.util.recyclerview.adapter.listener.OnItemClickListener
import java.lang.ref.WeakReference

class ItemPostRecyclerViewAdapter constructor(
    val listener: WeakReference<OnItemClickListener>,
    val list: MutableList<PostEntity> = mutableListOf()
) :
    RecyclerView.Adapter<ItemPostRecyclerViewAdapter.ItemPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        return ItemPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemPostViewHolder, position: Int) {
        val post = list[position]

        holder.bind(post)
        holder.itemView.setOnClickListener {
            listener.get()!!.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: List<PostEntity>){
        this.list.also{
            it.clear()
            it.addAll(list.toMutableList())
        }
        notifyDataSetChanged()
    }

    inner class ItemPostViewHolder constructor(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostEntity) {
            binding.post = post
        }
    }

}