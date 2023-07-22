package com.aplicacion.thecatsapp.data

import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.data.network.CatService
import retrofit2.Response
import javax.inject.Inject

class CatRepository @Inject constructor(private val apiService : CatService) {

    suspend fun getCats(): Response<ArrayList<Cat>> {
            return apiService.getCats()
    }

}