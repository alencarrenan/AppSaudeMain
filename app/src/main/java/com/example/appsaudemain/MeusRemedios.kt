package com.example.appsaudemain

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MeusRemedios : AppCompatActivity() {

    private val listaRemedios = mutableListOf<Remedio>()
    private val PREFS_NAME = "remedios_prefs"
    private val REMEDIOS_KEY = "lista_remedios"

    private var dataSelecionada = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meus_remedios)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editQuantidade = findViewById<EditText>(R.id.editQuantidade)
        val editHorario = findViewById<EditText>(R.id.editHorario)
        val txtData = findViewById<TextView>(R.id.txtDataSelecionada)
        val btnData = findViewById<Button>(R.id.btnEscolherData)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        carregarRemedios()

        btnData.setOnClickListener {
            val c = Calendar.getInstance()
            DatePickerDialog(this, { _, y, m, d ->
                dataSelecionada = "%02d/%02d/%04d".format(d, m + 1, y)
                txtData.text = dataSelecionada
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnAdicionar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val qtd = editQuantidade.text.toString().toIntOrNull() ?: 0
            val horario = editHorario.text.toString().trim()

            if (nome.isEmpty() || horario.isEmpty() || dataSelecionada.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val remedio = Remedio(nome, qtd, horario, dataSelecionada)
            listaRemedios.add(remedio)
            salvarRemedios()

            Toast.makeText(this, "Remédio salvo!", Toast.LENGTH_SHORT).show()

            editNome.text.clear()
            editQuantidade.text.clear()
            editHorario.text.clear()
            txtData.text = "Nenhuma data selecionada"
            dataSelecionada = ""
        }

        btnVoltar.setOnClickListener { finish() }
    }

    private fun salvarRemedios() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefs.edit().putString(REMEDIOS_KEY, Gson().toJson(listaRemedios)).apply()
    }

    private fun carregarRemedios() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val json = prefs.getString(REMEDIOS_KEY, null) ?: return
        val type = object : TypeToken<MutableList<Remedio>>() {}.type
        listaRemedios.addAll(Gson().fromJson(json, type))
    }
}
