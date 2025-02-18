package com.example.suitmediatest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class FirstScreen : AppCompatActivity() {
    private lateinit var inputName: EditText
    private lateinit var inputSentence: EditText
    private lateinit var buttonCheck: Button
    private lateinit var buttonNext: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        inputName = findViewById(R.id.name_field)
        inputSentence = findViewById(R.id.palindrome_field)
        buttonCheck = findViewById(R.id.button_check)
        buttonNext = findViewById(R.id.button_next)
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        buttonCheck.setOnClickListener {
            checkPalindrome()
        }

        buttonNext.setOnClickListener {
            val newName = inputName.text.toString()
            if (newName.isNotEmpty()) {
                saveUsername()
                navigateToSecondScreen()
            } else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPalindrome() {
        val sentence = inputSentence.text.toString().trim()
        val isPalindrome = isPalindrome(sentence)

        val message = if (isPalindrome) {
            "isPalindrome"
        } else {
            "not palindrome"
        }

        AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(message)
            .show()
    }

    private fun isPalindrome(sentence: String): Boolean {
        val cleaned = sentence.replace("\\s".toRegex(), "").lowercase()
        return cleaned == cleaned.reversed()
    }

    private fun saveUsername(){
        val newName = inputName.text.toString()
        if (newName.isNotEmpty()) {
            sharedPreferences.edit().putString("username", newName).apply()
            Toast.makeText(this, "Name updated!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToSecondScreen() {
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("USER_NAME", inputName.text.toString())
        startActivity(intent)
    }
}
