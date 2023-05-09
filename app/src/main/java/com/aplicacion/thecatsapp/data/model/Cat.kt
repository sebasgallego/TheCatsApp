package com.aplicacion.thecatsapp.data.model

import com.aplicacion.thecatsapp.utils.GlobalsVar.URL_IMG
import com.google.gson.annotations.SerializedName


class Cat {
    var id: String = ""
    var name: String = ""
    var origin: String = ""

    @SerializedName("affection_level")
    var affectionLevel: Int = 0

    @SerializedName("reference_image_id")
    var referenceImageId: String = ""

    var intelligence: Int = 0

    fun getImgUrl(referenceImageId: String): String {
        return "$URL_IMG$referenceImageId.jpg"
    }

}
