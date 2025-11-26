package com.example.appsaudemain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var loginMail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var buttonEntrar: Button
    private lateinit var buttonRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginMail = findViewById(R.id.loginMail)
        loginPassword = findViewById(R.id.loginPassword)
        buttonEntrar = findViewById(R.id.buttonEntrar)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)
        buttonEntrar.setOnClickListener { login() }
        buttonRegistrar.setOnClickListener {
            startActivity(Intent(this, telaRegistrar::class.java))
        }
    }

    private fun login() {
        val email = loginMail.text.toString()
        val senha = loginPassword.text.toString()

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val prefs = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
        val jsonString = prefs.getString("usuarios_json", "{}")
        val jsonObject = JSONObject(jsonString ?: "{}")

        if (!jsonObject.has(email)) {
            Toast.makeText(this, "Usuário não cadastrado!", Toast.LENGTH_SHORT).show()
            return
        }

        val userObj = jsonObject.getJSONObject(email)
        val senhaSalva = userObj.getString("senha")

        if (senha != senhaSalva) {
            Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Bem-vindo ${userObj.getString("nome")}!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MenuOpcoes::class.java))
        finish()
    }
}