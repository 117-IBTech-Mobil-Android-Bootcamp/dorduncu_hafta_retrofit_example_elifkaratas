package com.example.week4.service

interface BaseResponseHandlerInterface<T> {
    fun onSuccess(data : T)
    fun onFailure()
}