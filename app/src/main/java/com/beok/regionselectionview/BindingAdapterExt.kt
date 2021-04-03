package com.beok.regionselectionview

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("NotifyDataSetChanged")
@BindingAdapter("bind_replaceItem")
fun replaceItem(recyclerView: RecyclerView, item: StateFlow<List<Any>>?) =
    CoroutineScope(Dispatchers.Main).launch {
        if (item == null) return@launch

        item.collect {
            @Suppress("UNCHECKED_CAST")
            (recyclerView.adapter as? BaseListAdapter<Any>)?.submitList(it)
        }
    }
