package com.example.suitmediatest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class FirstScreen : AppCompatActivity() {
    private lateinit var inputName: EditText
    private lateinit var inputSentence: EditText
    private lateinit var buttonCheck: Button
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        inputName = findViewById(R.id.name_field)
        inputSentence = findViewById(R.id.palindrome_field)
        buttonCheck = findViewById(R.id.button_check)
        buttonNext = findViewById(R.id.button_next)

        buttonCheck.setOnClickListener {
            checkPalindrome()
        }

        buttonNext.setOnClickListener {
            navigateToSecondScreen()
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

    private fun navigateToSecondScreen() {
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra("USER_NAME", inputName.text.toString())
        startActivity(intent)
    }
}
