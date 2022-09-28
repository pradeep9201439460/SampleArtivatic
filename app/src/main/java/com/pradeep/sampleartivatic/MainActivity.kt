package com.pradeep.sampleartivatic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pradeep.sampleartivatic.adapter.CountryDetailsAdapter
import com.pradeep.sampleartivatic.databinding.ActivityMainBinding
import com.pradeep.sampleartivatic.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.simpleName
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        handleApiCall()
        
        binding.pullToRefresh.setOnRefreshListener {
            handleApiCall()
        }
    }

    private fun handleApiCall() {
        viewModel.country.observe(this, {
            when (it) {
                is Response.Loading -> {
                    showLoading(true)
                }
                is Response.Success -> {
                    it.data?.let {
                        this.title = it.title
                        val adapter = CountryDetailsAdapter(it.rows, this)
                        binding.recyclerView.adapter = adapter
                        binding.recyclerView.layoutManager = LinearLayoutManager(this)
                        binding.pullToRefresh.isRefreshing = false
                    }
                    showLoading(false)

                }
                is Response.Error -> {
                    showErrorScreen(true)
                    showLoading(false)
                    binding.pullToRefresh.isRefreshing = false

                }
            }
        })
    }


    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressView.visibility = View.VISIBLE
        } else {
            binding.progressView.visibility = View.GONE
        }
    }

    private fun showErrorScreen(show: Boolean) {
        if (show) {
            binding.errorViewImage.visibility = View.VISIBLE
        } else {
            binding.errorViewImage.visibility = View.GONE
        }
    }
}
