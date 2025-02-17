package com.example.suitmediatest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.suitmediatest.retrofit.ApiService
import com.example.suitmediatest.retrofit.UserResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThirdScreen : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var selectedUserName: TextView
    private lateinit var emptyStateTextView: TextView
    private lateinit var apiService: ApiService
    private var currentPage = 1
    private var totalPages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        emptyStateTextView = findViewById(R.id.emptyStateTextView)

        val gson: Gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(ApiService::class.java)

        userAdapter = UserAdapter(mutableListOf()) { user ->
            val selectedUserName = "${user.first_name} ${user.last_name}"
            val intent = Intent(this, SecondScreen::class.java).apply {
                putExtra("SELECTED_USER_NAME", selectedUserName)
            }
            startActivity(intent)
        }


        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!swipeRefreshLayout.isRefreshing && totalItemCount == lastVisibleItem + 1 && currentPage < totalPages) {
                    currentPage++
                    loadUsers()
                }
            }
        })

        loadUsers()
    }

    private fun loadUsers() {
        swipeRefreshLayout.isRefreshing = true
        apiService.getUsers(currentPage, 6).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        totalPages = userResponse.total_pages
                        userAdapter.addUsers(userResponse.data)
                        if (userAdapter.itemCount == 0) {
                            emptyStateTextView.visibility = View.VISIBLE
                        } else {
                            emptyStateTextView.visibility = View.GONE
                        }
                    }
                } else {
                    Toast.makeText(this@ThirdScreen, "Error loading users", Toast.LENGTH_SHORT).show()
                }
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@ThirdScreen, "Error loading users", Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun refreshData() {
        currentPage = 1
        userAdapter.clearUsers()
        loadUsers()
    }
}
