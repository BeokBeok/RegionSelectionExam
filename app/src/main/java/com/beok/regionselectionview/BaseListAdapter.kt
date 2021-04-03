package com.beok.regionselectionview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BaseListAdapter<T>(
    @LayoutRes private val layoutResourceID: Int,
    private val bindingID: Int,
    private val viewModel: Map<Int, ViewModel>,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(parent = parent, layoutResourceID = layoutResourceID, bindingID = bindingID)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(item = getItem(position), viewModel = viewModel)
    }
}