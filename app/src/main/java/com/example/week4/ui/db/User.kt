package com.example.week4.ui.db

import com.google.gson.annotations.SerializedName

class User(){
    @SerializedName("name")
    var name : String ?= null
    @SerializedName("email")
    var email : String?= null
    @SerializedName("password")
    var password : String?= null
    @SerializedName("age")
    var age : String?= null
    var token : String?= null

    companion object{
        var user : User ?= null

        fun getCurrentInstance() : User{
            /*user?.let {
                user = User()
            }?: kotlin.run {

            }*/

            if(user==null){
                user = User()
            }
            return user!!
        }
    }
    fun setUser(registeredUser : User){
        user?.age = registeredUser.age
        user?.name = registeredUser.name
        user?.email = registeredUser.email
        user?.password = registeredUser.password
        //user?.token = token
    }
}
