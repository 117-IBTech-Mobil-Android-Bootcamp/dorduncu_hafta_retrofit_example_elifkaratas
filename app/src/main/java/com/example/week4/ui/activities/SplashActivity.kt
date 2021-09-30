package com.example.week4.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.week4.R
import com.example.week4.service.ServiceConnector

class SplashActivity : AppCompatActivity() {
    private var i = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        waitSplash()
        ServiceConnector.init()
    }

    private fun waitSplash(){
        /// waiting 3 seconds
        Thread(Runnable {
            while (i < 2) {
                i += 1
                handler.post(Runnable {
                })
                try {
                    Thread.sleep(1000)
                    val langSelect = Intent(this, MainActivity::class.java)
                    startActivity(langSelect)
                    /// is back pressed, close app
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()
    }
}