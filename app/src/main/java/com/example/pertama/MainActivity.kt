package com.example.pertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var userDao : UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = AbsenDatabase.getDatabase(this)
        userDao = db.userDao()

        val daftar = findViewById<Button>(R.id.buttonDaftar)
        daftar.setOnClickListener{
            val intent = Intent(this, Pendaftaran::class.java)
            startActivity(intent)
            finish()
        }

        val submit = findViewById<Button>(R.id.buttonSubmit)
        submit.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextUsername)
            val password = findViewById<EditText>(R.id.editTextPassword)


            lifecycleScope.launch(Dispatchers.IO) {
                val user = userDao.getUserByUsernameAndPassword(username.text.toString(), password.text.toString())
                withContext(Dispatchers.Main){
                    if (user !=null){
                        val pindah = Intent(this@MainActivity, Dashboard::class.java)
                        pindah.putExtra("ID", user.id)
                        startActivity(pindah)
                    }else{
                        Toast.makeText(this@MainActivity,"Username/Password salah", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
    }
}