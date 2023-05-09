package com.aplicacion.thecatsapp.utils

import android.app.Activity
import com.aplicacion.thecatsapp.R
import java.net.HttpURLConnection.HTTP_UNAVAILABLE

class ViewHelper(activity: Activity) {

    private var mActivity: Activity? = activity

    init {
        mActivity = activity
    }

    fun processMsgError(code: Int): String {
        return when (code) {
            HTTP_UNAVAILABLE -> mActivity!!.getString(R.string.error_internet)
            else -> {
                mActivity!!.getString(R.string.error_server)
            }
        }
    }

}