package com.example.matchmate.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object InternetCheck {
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val active = connectivityManager.getNetworkCapabilities(network) ?: return false
        return active.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }
}