package com.example.carimahasiswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.JSONArrayRequestListener

class HomeActivity : AppCompatActivity() {

    private lateinit var logoutImageButton: ImageButton
    private lateinit var accountImageButton: ImageButton
    private lateinit var mahasiswaRecyclerView: RecyclerView
    private lateinit var cariEditText: EditText
    public lateinit var cari: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AndroidNetworking.initialize(this);

        logoutImageButton = findViewById(R.id.logoutImageButton)
        accountImageButton = findViewById(R.id.accountImageButton)
        mahasiswaRecyclerView = findViewById(R.id.mahasiswaRecyclerView)
        cariEditText = findViewById(R.id.cariEditText)

        logoutImageButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        accountImageButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        mahasiswaRecyclerView.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsViewModelSearch>()
        for(i in 1..10) {
            data.add(ItemsViewModelSearch(i))
        }

        val adapter = CustomAdapterSearch(data)
        mahasiswaRecyclerView.adapter = adapter
    }

//    private fun home() {
//        AndroidNetworking.get("https://api-frontend.kemdikbud.go.id/hit_mhs/{name}")
//            .addPathParameter("name", cariEditText.text.toString())
//            .setPriority(Priority.HIGH)
//            .build()
//            .getAsJSONArray(JSONArrayRequestListener() {
//
//            })
//    }
}