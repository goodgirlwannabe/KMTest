package com.example.suitmediatest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var selectedUserTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        nameTextView = findViewById(R.id.name_text_view)
        selectedUserTextView = findViewById(R.id.selected_user_text_view)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        loadUsername()

        val selectedUserName = intent.getStringExtra("SELECTED_USER_NAME") ?: "No User Selected"
        selectedUserTextView.text = selectedUserName

        val chooseUserButton: Button = findViewById(R.id.choose_user_button)
        chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdScreen::class.java)
            startActivity(intent)
        }

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener{
            val intent = Intent(this, FirstScreen::class.java)
            startActivity(intent)
        }
    }

    private fun loadUsername(){
        val savedName = sharedPreferences.getString("username", "Default Name")
        nameTextView.text = savedName
    }
}
