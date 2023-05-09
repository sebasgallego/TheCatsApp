package com.aplicacion.thecatsapp.data.network

import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.utils.GlobalsVar.GET_BREEDS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatApiClient {
    @GET(GET_BREEDS)
    @Headers("x-api-key: bda53789-d59e-46cd-9bc4-2936630fde39")
    suspend fun getCats(): Response<ArrayList<Cat>>
}