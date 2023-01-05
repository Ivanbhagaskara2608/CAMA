package com.example.carimahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var loginTextView: TextView
    private lateinit var registerButton: Button
    private lateinit var fullnameInputEditText: TextInputEditText
    private lateinit var usernameInputEditText: TextInputEditText
    private lateinit var passwordInputEditText: TextInputEditText
    private lateinit var fullnameInputLayout: TextInputLayout
    private lateinit var usernameInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        AndroidNetworking.initialize(applicationContext)

        loginTextView = findViewById(R.id.loginTextView)
        registerButton = findViewById(R.id.registerButton)
        fullnameInputEditText = findViewById(R.id.fullnameInputEditText)
        usernameInputEditText = findViewById(R.id.usernameInputEditText)
        passwordInputEditText = findViewById(R.id.passwordInputEditText)
        fullnameInputLayout = findViewById(R.id.fullnameInputLayout)
        usernameInputLayout = findViewById(R.id.usernameInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)

        loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val fullname = fullnameInputEditText.text.toString().trim()
        val username = usernameInputEditText.text.toString().trim()
        val password = passwordInputEditText.text.toString().trim()

        if (fullname.isEmpty()) {
            fullnameInputLayout.error = "fullname is required"
        } else if (username.isEmpty()) {
            usernameInputLayout.error = "username is required"
        } else if (password.isEmpty()) {
            passwordInputLayout.error = "password is required"
        } else {
            val jobj = JSONObject()
            try {
                jobj.put("fullname", fullname)
                jobj.put("username", username)
                jobj.put("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post("http://127.0.0.1:8000/api/register")
                .addJSONObjectBody(jobj)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object: JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        try {
                            if (response != null) {
                                if(response.getString("message").equals("Login Succeeded")) {
                                    Toast.makeText(this@RegisterActivity, "Login Berhasil", Toast.LENGTH_LONG).show()

                                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        } catch (e: JSONException) {
                            Log.d("error", e.toString())
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Log.d("error", anError.toString())
                    }
                })
        }
    }
}