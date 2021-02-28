package com.dz.foregroundservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.startService)
            .setOnClickListener {
                // Here we start our service
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    val intent = Intent(this,ExampleService::class.java)
                    startForegroundService(intent)
                } else {
                    val intent = Intent(this,ExampleService::class.java)
                    startService(intent)
                }
            }
    }
}