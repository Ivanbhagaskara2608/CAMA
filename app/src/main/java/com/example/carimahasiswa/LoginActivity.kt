package com.example.carimahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var daftarTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var usernameInputLayoutLogin: TextInputLayout
    private lateinit var passwordInputLayoutLogin: TextInputLayout
    private lateinit var usernameInputEditTextLogin: EditText
    private lateinit var passwordInputEditTextLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        daftarTextView = findViewById(R.id.daftarTextView)
        loginButton = findViewById(R.id.loginButton)
        usernameInputLayoutLogin = findViewById(R.id.usernameInputLayoutLogin)
        passwordInputLayoutLogin = findViewById(R.id.passwordInputLayoutLogin)
        usernameInputEditTextLogin = findViewById(R.id.usernameInputEditTextLogin)
        passwordInputEditTextLogin = findViewById(R.id.passwordInputEditTextLogin)

        daftarTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}