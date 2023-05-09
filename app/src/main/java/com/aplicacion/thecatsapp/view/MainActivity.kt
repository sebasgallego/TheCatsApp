package com.aplicacion.thecatsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aplicacion.thecatsapp.R
import com.aplicacion.thecatsapp.utils.ViewHelper
import com.aplicacion.thecatsapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Observe get list products
     */
    private fun setupObservers() {

        //Observe loading when get list products
        viewModel.loading.observe(this) {
            progressBa
        }

        //Observe get list products
        viewModel.productLiveData.observe(viewLifecycleOwner) { dataResponse ->
            if (dataResponse!!.results.size > 0) {
                adapter.newItems(ArrayList(dataResponse.results))
                binding!!.contentRecyclerView.rvGroup.success()
            } else {
                val emptyData: String = getString(R.string.empty_data)
                binding!!.contentRecyclerView.rvGroup.empty(emptyData)
            }
        }

        //Observe error msg when get list products
        viewModel.errorCode.observe(viewLifecycleOwner) { responseCode ->
            if (responseCode != null) {
                binding!!.contentRecyclerView.rvGroup.retry(
                    ViewHelper(requireActivity()).processMsgError(
                        responseCode
                    )
                )
            }
        }

    }

}