package com.aplicacion.thecatsapp.data

import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.data.network.CatApiClient
import com.aplicacion.thecatsapp.core.RetrofitHelper
import retrofit2.Response
import retrofit2.Retrofit

class CatRepository {

    private var catApiClient: CatApiClient

    /**
     * Repository
     */
    init {
        val retrofit: Retrofit = RetrofitHelper().getRetrofit()
        catApiClient = retrofit.create(CatApiClient::class.java)
    }

    suspend fun getCats(): Response<ArrayList<Cat>> {
            return catApiClient.getCats()
    }

}