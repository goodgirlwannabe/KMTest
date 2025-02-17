package com.example.suitmediatest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var selectedUserTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        nameTextView = findViewById(R.id.name_text_view)
        selectedUserTextView = findViewById(R.id.selected_user_text_view)

        val nameFromFirstScreen = intent.getStringExtra("USER_NAME") ?: "Default Name"
        val selectedUserName = intent.getStringExtra("SELECTED_USER_NAME") ?: "No User Selected"

        nameTextView.text = nameFromFirstScreen
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
}
