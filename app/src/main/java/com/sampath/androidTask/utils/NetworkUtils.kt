package com.sampath.androidTask.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtils @Inject constructor(private val context: Context) {
    @SuppressLint("ServiceCast")
    fun checkInternetConnection(): Boolean {
        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}