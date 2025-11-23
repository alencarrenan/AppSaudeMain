package com.example.appsaudemain

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MinhasDoencas : AppCompatActivity() {

    private val listaDoencas = mutableListOf<Doenca>()
    private lateinit var adapter: DoencaAdapter
    private val PREFS_NAME = "doencas_prefs"
    private val DOENCAS_KEY = "lista_doencas"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minhas_doencas)

        val editNomeDoenca = findViewById<EditText>(R.id.editNomeDoenca)
        val editRemedio = findViewById<EditText>(R.id.editRemedio)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionarDoenca)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarDoenca)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDoencas)

        adapter = DoencaAdapter(listaDoencas) { salvarDoencas() }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        carregarDoencas()

        btnAdicionar.setOnClickListener {
            val nome = editNomeDoenca.text.toString()
            val remedio = editRemedio.text.toString()
            if (nome.isNotEmpty() && remedio.isNotEmpty()) {
                listaDoencas.add(Doenca(nome, remedio))
                adapter.notifyDataSetChanged()
                salvarDoencas()
                editNomeDoenca.text.clear()
                editRemedio.text.clear()
            }
        }

        btnVoltar.setOnClickListener { finish() }
    }

    private fun salvarDoencas() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(listaDoencas)
        editor.putString(DOENCAS_KEY, json)
        editor.apply()
    }

    private fun carregarDoencas() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val json = prefs.getString(DOENCAS_KEY, null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<Doenca>>() {}.type
            val listaSalva: MutableList<Doenca> = Gson().fromJson(json, type)
            listaDoencas.addAll(listaSalva)
            adapter.notifyDataSetChanged()
        }
    }
}
