package com.aplicacion.thecatsapp.data.model

import com.google.gson.annotations.SerializedName


class Cat {
    var id: String = ""
    var name: String = ""
    var origin: String = ""
    @SerializedName("affection_level")
    var affectionLevel: Int = 0
    var intelligence: Int = 0
}
