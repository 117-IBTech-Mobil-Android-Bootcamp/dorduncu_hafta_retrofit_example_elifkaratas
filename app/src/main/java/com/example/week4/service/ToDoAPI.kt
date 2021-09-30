package com.example.week4.service

import com.example.week4.ui.db.RegisterResponse
import com.example.week4.ui.db.TaskResponse
import com.example.week4.ui.db.User
import okhttp3.internal.concurrent.Task
import retrofit2.Call
import retrofit2.http.*

interface ToDoAPI {

    @POST("user/register")
    fun registerUser(@Body params: MutableMap<String, Any>) : Call<RegisterResponse>

    @GET("user/me")
    fun loggedUser() : Call<User>

    ///////////

    @POST("task")
    fun addTask(@Body params: HashMap<String, Any>) : Call<Task>

    @GET("task?limit=2&skip=10")
    fun getTaskByPagination() : Call<List<TaskResponse>>

    @PUT("task/{Id}")
    fun updateTask(@Path("Id") taskId: String): Call<Task>

    @DELETE("task/{Id}")
    fun deleteTask(@Path("Id") taskId: String): Call<Task>
}