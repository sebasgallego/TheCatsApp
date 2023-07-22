package com.aplicacion.thecatsapp.domain


import com.aplicacion.thecatsapp.data.CatRepository
import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.data.network.ApiResponse
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val repository: CatRepository) {

    suspend operator fun invoke(): ApiResponse<ArrayList<Cat>> {
        try {
            val response = repository.getCats()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return ApiResponse(body = body)
            }
            return ApiResponse(
                errorMessage = response.message(),
                httpCode = response.code(),
                body = null
            )
        } catch (e: IOException) {
            return ApiResponse(
                errorMessage = e.message ?: e.toString(),
                httpCode = HttpURLConnection.HTTP_UNAVAILABLE
            )
        } catch (e: Exception) {
            return ApiResponse(
                errorMessage = e.message ?: e.toString(),
                httpCode = HttpURLConnection.HTTP_INTERNAL_ERROR
            )
        }
    }
}