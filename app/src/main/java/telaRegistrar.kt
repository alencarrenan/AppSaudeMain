package com.example.appsaudemain
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class telaRegistrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

    }
    fun getData() {
        val inputNome = findViewById<EditText>(R.id.inputNome)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputSenha = findViewById<EditText>(R.id.inputSenha)
        val buttonCadastrar = findViewById<Button>(R.id.buttonCadastrar)

        buttonCadastrar.setOnClickListener {

            val nome = inputNome.text.toString()
            val email = inputEmail.text.toString()
            val senha = inputSenha.text.toString()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Dados do Usuário")
            builder.setMessage("Nome: $nome\nEmail: $email\nSenha: $senha")
            builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            builder.show()
        }
    }


    fun voltarMenuInicial() {
        val buttonVoltar: Button = findViewById(R.id.buttonVoltar)
        buttonVoltar.setOnClickListener {
            finish() // Fecha esta Activity e volta para a MainActivity

        }
    }
}