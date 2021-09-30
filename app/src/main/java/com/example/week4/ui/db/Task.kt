package com.example.week4.ui.db

import com.google.gson.annotations.SerializedName

class Task(){
    @SerializedName("description")
    var description : String ?= null
    @SerializedName("id")
    var id : String?= null

    companion object{
        var task : Task ?= null
    }
    fun setTask(createdTask : Task){
        task?.description = createdTask.description
    }
}