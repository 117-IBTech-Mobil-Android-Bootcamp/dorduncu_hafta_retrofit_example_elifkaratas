package com.example.week4.ui.testresult

import com.example.week3.BaseFragment
import com.example.week4.R
import com.example.week4.utils.getString
import com.example.week4.utils.toNextFragment
import com.example.week4.utils.toast
import kotlinx.android.synthetic.main.login_fragment.*
import android.util.Patterns
import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment : BaseFragment() {
    override fun getLayoutID() = R.layout.login_fragment
    override fun prepareView() {
        loginButton.setOnClickListener(){
            hitLoginEndpoint()
        }
        textViewRegister.setOnClickListener {
            toNextFragment(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun hitLoginEndpoint() {
        val email = editTextEmailAddress.getString()
        val password = editTextPassword.getString()

        if(allFieldsAreValid(email,password)){
            toast("Logged to account.")
        }else{
            toast("Check fields.")
        }
    }

    private fun allFieldsAreValid(email : String, password : String) : Boolean{
        var isCorrect = true

        if(email.isEmpty() || !isValidEmail(email)) isCorrect = false
        if(password.isEmpty()) isCorrect = false

        return isCorrect
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}