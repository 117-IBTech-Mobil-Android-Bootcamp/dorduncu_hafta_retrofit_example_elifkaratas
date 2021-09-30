package com.example.week4.service

import android.net.DnsResolver
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseCallBack<T> : Callback<T>, BaseResponseHandlerInterface<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {

        if(response.isSuccessful) {
            response.body()?.let {
                onSuccess(it)
            } ?: run {
                onFailure()
            }
        }
        else
            onFailure()
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure()
    }

    override fun onSuccess(data: T) {
        Log.d("mysuccess","success triggered")
    }

    override fun onFailure() {
        Log.d("failure","failure triggered")
    }
}