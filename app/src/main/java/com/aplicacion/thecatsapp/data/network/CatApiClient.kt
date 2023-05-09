package com.aplicacion.thecatsapp.data.network

import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.utils.GlobalsVar.GET_BREEDS
import retrofit2.Response
import retrofit2.http.GET

interface CatApiClient {
    @GET(GET_BREEDS)
    suspend fun getCats(): Response<ArrayList<Cat>>
}