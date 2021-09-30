package com.example.week4.service

import com.example.week4.ui.db.User
import com.example.week4.utils.BASE_URL_FOR_TODO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor

class ServiceConnector {

    companion object{
        var retrofit : Retrofit ?= null
        lateinit var restInterface : ToDoAPI

        fun init () : Retrofit {

            /// logging interceptor
            val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }

            /// adding login interceptor to okhttp
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            /// creating retrofit client to correspond with Backend services
            retrofit = Retrofit.Builder().baseUrl(BASE_URL_FOR_TODO)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            restInterface = retrofit?.create(ToDoAPI::class.java)!!

            return retrofit!!
        }
    }

    class AuthenticationInterceptor : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder().addHeader("Autherization", User.getCurrentInstance().token ?: "").build()
            return chain.proceed(request)
        }

    }
}