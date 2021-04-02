package com.beok.regionselectionview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter(
    @LayoutRes private val layoutResourceID: Int,
    private val bindingID: Int,
    private val viewModel: Map<Int, ViewModel>
) : RecyclerView.Adapter<BaseViewHolder>() {

    private val itemList = mutableListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent = parent, layoutResourceID = layoutResourceID, bindingID = bindingID)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(item = itemList[position], viewModel = viewModel)
    }

    override fun getItemCount(): Int = itemList.size

    fun replaceItem(item: List<Any>) {
        itemList.run {
            clear()
            addAll(item)
        }
    }
}