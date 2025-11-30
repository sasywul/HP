package com.example.pertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import org.w3c.dom.Text

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val nama_depan =intent.getStringExtra("Nama_Depan")
        val nama_belakang = intent.getStringExtra("Nama_Belakang")
        val username = intent.getStringExtra("USERNAME")
        val email = intent.getStringExtra("EMAIL")

        val tvNamaDepan = findViewById<TextView>(R.id.TvNamaDepan)
        val tvNamaBelakang = findViewById<TextView>(R.id.tvNamaBelakang)
        val tvUsername = findViewById<TextView>(R.id.username)
        val tvEmail = findViewById<TextView>(R.id.email)

        tvNamaDepan.text = nama_depan
        tvNamaBelakang.text = nama_belakang
        tvUsername.text = username
        tvEmail.text = email





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}