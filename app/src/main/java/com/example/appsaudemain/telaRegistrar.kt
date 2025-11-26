package com.example.appsaudemain

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class telaRegistrar : AppCompatActivity() {

    private lateinit var inputNome: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputSenha: EditText
    private lateinit var buttonCadastrar: Button
    private lateinit var buttonVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        inputNome = findViewById(R.id.inputNome)
        inputEmail = findViewById(R.id.inputEmail)
        inputSenha = findViewById(R.id.inputSenha)
        buttonCadastrar = findViewById(R.id.buttonCadastrar)
        buttonVoltar = findViewById(R.id.buttonVoltar)

        buttonCadastrar.setOnClickListener {
            registrarUsuario()
        }

        buttonVoltar.setOnClickListener {
            finish()
        }
    }

    private fun registrarUsuario() {
        val nome = inputNome.text.toString()
        val email = inputEmail.text.toString()
        val senha = inputSenha.text.toString()

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }

        val prefs = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
        val jsonString = prefs.getString("usuarios_json", "{}")
        val jsonObject = JSONObject(jsonString ?: "{}")

        if (jsonObject.has(email)) {
            Toast.makeText(this, "Usuário já cadastrado!", Toast.LENGTH_SHORT).show()
            return
        }

        val userObj = JSONObject()
        userObj.put("nome", nome)
        userObj.put("senha", senha)

        jsonObject.put(email, userObj)

        prefs.edit().putString("usuarios_json", jsonObject.toString()).apply()

        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
