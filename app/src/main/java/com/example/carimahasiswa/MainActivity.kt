package com.example.carimahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set a delay for the splash screen
        val splashDelay: Long = 3000 // 3 seconds

        Handler().postDelayed({
            // Launch the main activity
            startActivity(Intent(this, LoginActivity::class.java))
            // Close the splash screen activity
            finish()
        }, splashDelay)
    }
}