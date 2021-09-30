package com.example.week4.ui.testresult

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.example.week3.BaseFragment
import com.example.week4.R
import com.example.week4.service.BaseCallBack
import com.example.week4.service.ServiceConnector
import com.example.week4.ui.db.RegisterResponse
import com.example.week4.utils.*
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : BaseFragment(){
    override fun getLayoutID(): Int = R.layout.register_fragment

    override fun prepareView() {
        registerButton.setOnClickListener(){
            hitLoginEndpoint()
        }
    }

    private fun hitLoginEndpoint() {
        val email = editTextEmailAddressRegister.getString()
        val password = editTextPasswordRegister.getString()
        val name = editTextNameRegister.getString()
        val age = editTextAgeRegister.getString()

        if(allFieldsAreValid(name,email,password,age)){
            val params = mutableMapOf<String,Any>().apply {
                put("name",name)
                put("email",email)
                put("password",password)
                put("age",age)
            }

            ServiceConnector.restInterface.registerUser(params).enqueue(object : BaseCallBack<RegisterResponse>(){
                override fun onSuccess(registerResponse: RegisterResponse) {
                    super.onSuccess(registerResponse)
                    Log.e("token: ", registerResponse.token)

                    saveDataAsString(USER_TOKEN,registerResponse.token)

                    toNextFragment(R.id.action_registerFragment_to_loginFragment)
                }
                override fun onFailure() {
                    super.onFailure()
                    toast("Server failure.")
                }
            })

            toast("Created account.")
        }else{
            toast("Check fields.")
        }
    }

    private fun allFieldsAreValid(name: String, email : String, password : String, age : String) : Boolean{
        var isCorrect = true

        if(email.isEmpty() || !isValidEmail(email)) isCorrect = false
        if(password.isEmpty()) isCorrect = false
        if(age.isEmpty()) isCorrect = false
        if(name.isEmpty()) isCorrect = false

        return isCorrect
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}