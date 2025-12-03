                package com.example.betapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val questionfield = findViewById<EditText>(R.id.entrer_question)
        val reponsefield = findViewById<EditText>(R.id.entrer_reponse)
        val save_icon = findViewById<ImageView>(R.id.save)
        val cancel_icon = findViewById<ImageView>(R.id.cancel)

        cancel_icon.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }


        save_icon.setOnClickListener {
            val userquestion = questionfield.text.toString()
            val    userreponse = reponsefield.text.toString()

            val data = Intent() // create a new Intent, this is where we will put our data

            data.putExtra(
                "question_key",
                userquestion
            ) // puts one string into the Intent, with the key as 'string1'

            data.putExtra(
                "reponse_key",
                userreponse
            ) // puts another string into the Intent, with the key as 'string2

            setResult(RESULT_OK, data) // set result code and bundle data for response

            finish()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}