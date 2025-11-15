package com.example.appsaudemain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste das bordas
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // CHAMA A FUNÇÃO login()
        login()
        cadastro()
    }

    fun login() {
        val buttonEntrar: Button = findViewById(R.id.buttonEntrar)

        buttonEntrar.setOnClickListener {
            val intent = Intent(this, menuOpcoes::class.java)
            startActivity(intent)

        }

    }

    fun cadastro() {
        val buttonRegistrar: Button = findViewById(R.id.buttonRegistrar)

        buttonRegistrar.setOnClickListener {
            val intent = Intent(this, telaRegistrar::class.java)
            startActivity(intent)
        }
    }
}
