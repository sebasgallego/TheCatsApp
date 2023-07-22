package com.aplicacion.thecatsapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplicacion.thecatsapp.R
import com.aplicacion.thecatsapp.adapter.CatAdapter
import com.aplicacion.thecatsapp.databinding.ActivityMainBinding
import com.aplicacion.thecatsapp.utils.ViewHelper
import com.aplicacion.thecatsapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        setupVieModel()
        setupObservers()
    }

    private fun setupVieModel(){
        viewModel.getCats()
    }

    /**
     * Observe get list cats
     */
    private fun setupObservers() {

        //Observe loading when get list cats
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        //Observe get list cats
        viewModel.catLiveData.observe(this) { dataResponse ->
            if (dataResponse!!.size > 0) {
                adapter.newItems(ArrayList(dataResponse))
            }
        }

        //Observe error msg when get list cats
        viewModel.errorCode.observe(this) { responseCode ->
            if (responseCode != null) {
                ViewHelper(this).processMsgError(
                    responseCode
                )
            }
        }

    }

    /**
     * init Recycler View
     */
    @SuppressLint("SetTextI18n")
    private fun initRecyclerView() {
        adapter = CatAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

}