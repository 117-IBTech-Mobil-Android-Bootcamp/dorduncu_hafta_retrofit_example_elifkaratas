package com.example.week4.ui.activities

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.week4.R
import com.example.week4.service.BaseCallBack
import com.example.week4.service.ServiceConnector
import com.example.week4.ui.db.User
import com.example.week4.utils.gone
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity() {
    private var token : String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment)

        if(isLoggedIn()){
            // Configure the navigation
            User.getCurrentInstance().token = token
            ServiceConnector.restInterface.loggedUser().enqueue(object : BaseCallBack<User>(){
                override fun onSuccess(data: User) {
                    super.onSuccess(data)
                    User.getCurrentInstance().setUser(data)
                    Log.e("tokken setting", "successfull")
                    navController.navigate(R.id.homePage)
                }

                override fun onFailure() {
                    super.onFailure()
                    Log.e("tokken setting", "failure")
                }
            })

        }else{
            // Configure the navigation
            //navController.navigate(R.id.loginFragment)
        }
    }
    private fun getToken() : String {
        // Retrieving the value using its keys the file name
        // must be same in both saving and retrieving the data
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        token = sh.getString("USER_TOKEN", "")

        return token ?: ""
    }

    open fun isLoggedIn() : Boolean{
        val token = getToken()
        return if(token.isEmpty()) false else true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 2) {
            // If there are back-stack entries, leave the FragmentActivity
            // implementation take care of them.
            supportFragmentManager.popBackStack()
        } else {
            // Ask user if user wants to leave
            AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Do you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(
                    android.R.string.yes,
                    DialogInterface.OnClickListener { arg0, arg1 -> finish()})
                .create().show()
        }
    }
}