package com.example.growigh.Controller

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.growigh.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growigh.Adapter.CustomAdapter
import com.example.growigh.Model.NewsApiResponse
import com.example.growigh.Model.NewsHeadlines
import com.example.growigh.OnFetchDataListener
import com.example.growigh.RequestManager
import com.example.growigh.SelectListener

//class HomeActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: CustomAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = ContextCompat.getColor(this, R.color.tertiary)
//
//            // Set the icons and information in the status bar to appear in black color
//            val decorView: View = window.decorView
//            var flags: Int = decorView.systemUiVisibility
//            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            decorView.systemUiVisibility = flags
//        }
//
//        val manager = RequestManager(this)
//        manager.getNewsHeadlines(listener, "general", "")
//    }
//
//    private val listener: OnFetchDataListener<NewsApiResponse> = object : OnFetchDataListener<NewsApiResponse> {
//        override fun onFetchData(list: List<NewsHeadlines>, message: String) {
//            showNews(list)
//        }
//
//        override fun onError(message: String) {
//            // Handle error
//        }
//    }
//
//    private fun showNews(list: List<NewsHeadlines>) {
//        recyclerView = findViewById(R.id.rcvFacts)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = GridLayoutManager(this, 1)
//        adapter = CustomAdapter(this, list)
//        recyclerView.adapter = adapter
//    }
//}


class HomeActivity : AppCompatActivity(), SelectListener, View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var b4: Button
    private lateinit var b5: Button
    private lateinit var b6: Button
    private lateinit var b7: Button
    private lateinit var searchView: androidx.appcompat.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.tertiary)

            // Set the icons and information in the status bar to appear in black color
            val decorView = window.decorView
            var flags = decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            decorView.systemUiVisibility = flags
        }

        searchView = findViewById(R.id.searchViewHome)
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val manager = RequestManager(this@HomeActivity)
                manager.getNewsHeadlines(listener, "general", query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        b1 = findViewById(R.id.btn_1)
        b1.setOnClickListener(this)
        b2 = findViewById(R.id.btn_2)
        b2.setOnClickListener(this)
        b3 = findViewById(R.id.btn_3)
        b3.setOnClickListener(this)
        b4 = findViewById(R.id.btn_4)
        b4.setOnClickListener(this)
        b5 = findViewById(R.id.btn_5)
        b5.setOnClickListener(this)
        b6 = findViewById(R.id.btn_6)
        b6.setOnClickListener(this)
        b7 = findViewById(R.id.btn_7)
        b7.setOnClickListener(this)

        val manager = RequestManager(this)
        manager.getNewsHeadlines(listener, "general", "")
    }

    private val listener = object : OnFetchDataListener<NewsApiResponse> {
        override fun onFetchData(list: List<NewsHeadlines>, message: String) {
            if (list.isEmpty()) {
                Toast.makeText(this@HomeActivity, "No Data Found !!", Toast.LENGTH_SHORT).show()
            } else {
                showNews(list)
            }
        }

        override fun onError(message: String) {
            Toast.makeText(this@HomeActivity, "An Error Occured !!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNews(list: List<NewsHeadlines>) {
        recyclerView = findViewById(R.id.rcvFacts)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        adapter = CustomAdapter(this, list, this)
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View) {
        val button = v as Button
        val category = button.text.toString()

        val manager = RequestManager(this)
        manager.getNewsHeadlines(listener, category, "")
    }

    override fun onNewsClicked(headlines: NewsHeadlines) {
        startActivity(Intent(this@HomeActivity, DetailsActivity::class.java)
            .putExtra("data", headlines))
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this@HomeActivity, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
