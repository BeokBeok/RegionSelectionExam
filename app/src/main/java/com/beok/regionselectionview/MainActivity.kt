package com.beok.regionselectionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
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
        binding.rvMainContent.adapter = BaseAdapter(
            layoutResourceID = R.layout.rv_item_city,
            bindingID = BR.area,
            viewModel = mapOf(BR.viewModel to viewModel)
        )
    }

    private fun showContent() {
        viewModel.fetch()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}