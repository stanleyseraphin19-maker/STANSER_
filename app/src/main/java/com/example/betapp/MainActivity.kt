package com.example.betapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (data != null) { // Check that we have data returned
            val question = data.getStringExtra("question_key") // 'string1' needs to match the key we used when we put the string in the Intent
            val reponse = data.getStringExtra("reponse_key")

            // Log the value of the strings for easier debugging
            Log.i("MainActivity", "question: $question")
            Log.i("MainActivity", "reponse: $reponse")

            findViewById<TextView>(R.id.question).text=question
            findViewById<TextView>(R.id.Reponse).text=reponse
        } else {
            Log.i("MainActivity", "Returned null data from AddCardActivity")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val questionText = findViewById<TextView>(R.id.question)
        val reponseText = findViewById<TextView>(R.id.Reponse)
        val add_icon = findViewById<ImageView>(R.id.add)

        add_icon.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
        }

        questionText.setOnClickListener {
            questionText.visibility = View.INVISIBLE
            reponseText.visibility = View.VISIBLE 
        }
        reponseText.setOnClickListener {
            reponseText.visibility = View.INVISIBLE
            questionText.visibility = View.VISIBLE
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}