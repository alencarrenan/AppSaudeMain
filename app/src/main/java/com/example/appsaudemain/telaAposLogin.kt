package com.example.appsaudemain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class menuOpcoes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apos_login)

        // Botão Meus Remédios
        val btnRemedios = findViewById<Button>(R.id.button4)
        btnRemedios.setOnClickListener {
            val intent = Intent(this, MeusRemedios::class.java)
            startActivity(intent)
        }

        // Botão Minhas Doenças
        val btnMinhasDoencas = findViewById<Button>(R.id.button7)
        btnMinhasDoencas.setOnClickListener {
            val intent = Intent(this, MinhasDoencas::class.java)
            startActivity(intent)
        }

        // Botão Voltar, se quiser
        val btnVoltar = findViewById<Button>(R.id.buttonVoltar1)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}

