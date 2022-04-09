package com.dngwjy.newsapp.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class GeneralRvViewBinding<T : Any>(
    private val diffCallback: DiffCallback,
    private val inflate: (layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> ViewBinding,
    private val bind: (item: T, pos: Int, binding: ViewBinding) -> Unit,
    private val onClick: (item: T, pos: Int, binding: ViewBinding) -> Unit
) : RecyclerView.Adapter<GeneralRvViewBinding<T>.ItemViewHolder>() {
    private val listData = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(
            listData[holder.adapterPosition],
            bind,
            onClick
        )
    }

    override fun getItemCount(): Int = listData.size

    fun setData(data: List<T>) {
        calculateDiff(data)
    }

    private fun calculateDiff(newData: List<T>) {
        diffCallback.setList(listData, newData)
        val result = DiffUtil.calculateDiff(diffCallback)
        with(listData) {
            clear()
            addAll(newData)
        }
        result.dispatchUpdatesTo(this)
    }

    inner class ItemViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder((binding).root) {
        fun bindView(
            item: T,
            bind: (item: T, pos: Int, binding: ViewBinding) -> Unit,
            onClick: (item: T, pos: Int, binding: ViewBinding) -> Unit
        ) {
            with(itemView) {
                bind.invoke(item, adapterPosition, binding)
                setOnClickListener { onClick(item, adapterPosition, binding) }
            }
        }
    }
}