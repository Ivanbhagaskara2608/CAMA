package com.example.carimahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    private lateinit var logoutImageButton: ImageButton
    private lateinit var accountImageButton: ImageButton
    private lateinit var mahasiswaRecyclerView: RecyclerView
    private lateinit var cariEditText: EditText
    private lateinit var cariButton: Button
    private val list = ArrayList<ItemsViewModelSearch>()
    private lateinit var customAdapterSearch: CustomAdapterSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AndroidNetworking.initialize(this);

        logoutImageButton = findViewById(R.id.logoutImageButton)
        accountImageButton = findViewById(R.id.accountImageButton)
        mahasiswaRecyclerView = findViewById(R.id.mahasiswaRecyclerView)
        cariEditText = findViewById(R.id.cariEditText)
        cariButton = findViewById(R.id.cariButton)

        logoutImageButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        accountImageButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        cariButton.setOnClickListener {
            getData()
            initAdapter()
        }

//        mahasiswaRecyclerView.layoutManager = LinearLayoutManager(this)
//        val data = ArrayList<ItemsViewModelSearch>()
//        for(i in 1..10) {
//            data.add(ItemsViewModelSearch())
//        }
//
//        val adapter = CustomAdapterSearch(data)
//        mahasiswaRecyclerView.adapter = adapter
    }

    private fun getData() {
        AndroidNetworking.get("https://api-frontend.kemdikbud.go.id/hit_mhs/{name}")
            .addPathParameter("name", cariEditText.text.toString())
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    val data = JSONArray(response!!.getString("mahasiswa"))
                    try {
                        for (i in 0 until data.length()) {
                            val value = data.getJSONObject(i)
                            val text = value.getString("text")
                            val mahasiswa = ItemsViewModelSearch(text)
                            list.add(mahasiswa)
                        }
                    } catch (e: JSONException){
                        Log.e("jsonErr", e.message.toString())
                    }
                    mahasiswaRecyclerView.adapter = customAdapterSearch
                }

                override fun onError(anError: ANError?) {
                    Log.e("TAG", anError.toString())
                }

            })
    }

    private fun initAdapter(){
        customAdapterSearch = CustomAdapterSearch(list)
        val layoutManager = LinearLayoutManager(this)
        mahasiswaRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}