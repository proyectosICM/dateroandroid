package com.icm.dateroandorid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.icm.dateroandorid.interfaces.JsonPlaceHolderApi
import com.icm.dateroandorid.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Panel1Activity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var posts: ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel1)

        // Inicializa el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        posts = ArrayList()
        adapter = ItemAdapter(this)
        recyclerView.adapter = adapter

        val webViewMap = findViewById<WebView>(R.id.webViewMap)
        val webSettings: WebSettings = webViewMap.settings

        webViewMap.webViewClient = WebViewClient()
        webSettings.javaScriptEnabled = true
        webViewMap.loadUrl("https://www.google.com/maps/@-12.0375731,-76.9616057,14.75z?entry=ttu")

        getPost()
        bottomButtons()
    }

    private fun bottomButtons(){
        val btnPanel1 = findViewById<AppCompatButton>(R.id.btnPanel1)
        val btnPanel2 = findViewById<AppCompatButton>(R.id.btnPanel2)

        btnPanel1.setOnClickListener{
            val intent = Intent(this, Panel1Activity::class.java)
            startActivity(intent)
        }

        btnPanel2.setOnClickListener{
            val intent = Intent(this, Panel2Activity::class.java)
            startActivity(intent)
        }
    }


    private fun getPost() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        val call = jsonPlaceHolderApi.getPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    Log.e("Panel1Activity", "Response not successful: ${response.errorBody()?.string()}")
                    return
                }

                val posts = response.body()
                if (posts != null) {
                    adapter.adicionarListaPost(posts as ArrayList<Post>)
                    Log.d("Panel1Activity", "Posts loaded: ${posts.size}")
                } else {
                    Log.e("Panel1Activity", "No posts received")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("Panel1Activity", "API call failed: ${t.message}")
            }
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}
