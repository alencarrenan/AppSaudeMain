package com.example.appsaudemain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuOpcoes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apos_login)

        val btnRemedios = findViewById<Button>(R.id.button4)
        btnRemedios.setOnClickListener {
            startActivity(Intent(this, MeusRemedios::class.java))
        }

        val btnMinhasDoencas = findViewById<Button>(R.id.button7)
        btnMinhasDoencas.setOnClickListener {
            startActivity(Intent(this, MinhasDoencas::class.java))
        }

        val btnCalendario = findViewById<Button>(R.id.button6)
        btnCalendario.setOnClickListener {
            startActivity(Intent(this, CalendarioActivity::class.java))
        }

        val btnCadastro = findViewById<Button>(R.id.button8)
        btnCadastro.setOnClickListener {
            startActivity(Intent(this, telaRegistrar::class.java))
        }

        val btnVoltar = findViewById<Button>(R.id.buttonVoltar1)
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
