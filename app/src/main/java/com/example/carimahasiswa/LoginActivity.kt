package com.example.carimahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject

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
        AndroidNetworking.initialize(applicationContext)

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
            login()
        }
    }

    private fun login() {
        val username = usernameInputEditTextLogin.text.toString().trim()
        val password = passwordInputEditTextLogin.text.toString().trim()

        if (usernameInputEditTextLogin.text.isEmpty()) {
            usernameInputLayoutLogin.error = "Username is required"
        } else if (passwordInputEditTextLogin.text.isEmpty()) {
            passwordInputLayoutLogin.error = "Password is required"
        } else {
            val jobj = JSONObject()
            try {
                jobj.put("username", username)
                jobj.put("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post("http://192.168.0.110:8000/api/login")
                .addJSONObjectBody(jobj)
                .addHeaders("Accept", "application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object: JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        try {
                            if (response != null) {
                                if(response.getString("message").equals("Login Succeeded")) {
                                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_LONG).show()

                                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
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