package com.beok.regionselectionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.beok.regionselectionview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupRecyclerView()
        showContent()
    }

    private fun setupRecyclerView() {
        binding.rvMainCity.adapter = BaseListAdapter(
            layoutResourceID = R.layout.rv_item_city,
            bindingID = BR.area,
            viewModel = mapOf(BR.viewModel to viewModel),
            diffUtil = object : DiffUtil.ItemCallback<Area>() {
                override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
                    return oldItem == newItem
                }
            }
        )
        binding.rvMainDistrict.adapter = BaseListAdapter(
            layoutResourceID = R.layout.rv_item_district,
            bindingID = BR.area,
            viewModel = mapOf(BR.viewModel to viewModel),
            diffUtil = object : DiffUtil.ItemCallback<Area>() {
                override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
                    return oldItem == newItem
                }
            }
        )
    }

    private fun showContent() {
        viewModel.fetchCity()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}