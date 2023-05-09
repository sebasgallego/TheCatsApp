package com.aplicacion.thecatsapp.data.model

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("results") var results: ArrayList<Cat>
)