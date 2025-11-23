package com.example.appsaudemain

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MeusRemedios : AppCompatActivity() {

    private val listaRemedios = mutableListOf<Remedio>()
    private lateinit var adapter: RemedioAdapter
    private val PREFS_NAME = "remedios_prefs"
    private val REMEDIOS_KEY = "lista_remedios"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meus_remedios)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editQuantidade = findViewById<EditText>(R.id.editQuantidade)
        val editHorario = findViewById<EditText>(R.id.editHorario)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerRemedios)

        adapter = RemedioAdapter(listaRemedios) { salvarRemedios() }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        carregarRemedios()

        btnAdicionar.setOnClickListener {
            val nome = editNome.text.toString()
            val qtd = editQuantidade.text.toString().toIntOrNull() ?: 0
            val horario = editHorario.text.toString()
            if (nome.isNotEmpty() && horario.isNotEmpty()) {
                listaRemedios.add(Remedio(nome, qtd, horario))
                adapter.notifyDataSetChanged()
                salvarRemedios()
                editNome.text.clear()
                editQuantidade.text.clear()
                editHorario.text.clear()
            }
        }

        btnVoltar.setOnClickListener { finish() }
    }

    private fun salvarRemedios() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(listaRemedios)
        editor.putString(REMEDIOS_KEY, json)
        editor.apply()
    }

    private fun carregarRemedios() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val json = prefs.getString(REMEDIOS_KEY, null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<Remedio>>() {}.type
            val listaSalva: MutableList<Remedio> = Gson().fromJson(json, type)
            listaRemedios.addAll(listaSalva)
            adapter.notifyDataSetChanged()
        }
    }
}
