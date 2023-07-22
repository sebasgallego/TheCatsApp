package com.aplicacion.thecatsapp.data.network

import com.aplicacion.thecatsapp.data.model.Cat
import retrofit2.Response
import javax.inject.Inject

class CatService @Inject constructor(private val apiClient: CatApiClient) {

    suspend fun getCats(): Response<ArrayList<Cat>> {
        return apiClient.getCats()
    }

}