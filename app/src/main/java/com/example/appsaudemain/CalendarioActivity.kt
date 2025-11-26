package com.example.appsaudemain

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CalendarioActivity : AppCompatActivity() {

    private val PREFS_NAME = "remedios_prefs"
    private val REMEDIOS_KEY = "lista_remedios"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val txtListaDia = findViewById<TextView>(R.id.txtListaDia)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->

            val dataSelecionada = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
            val remedios = carregarRemedios()
            val remediosDoDia = remedios.filter { it.data == dataSelecionada }

            if (remediosDoDia.isEmpty()) {
                txtListaDia.text = "Nenhum remédio neste dia."
            } else {
                txtListaDia.text = remediosDoDia.joinToString("\n") {
                    "• ${it.nome} — ${it.horario} (Qtd: ${it.quantidade})"
                }
            }
        }
    }

    private fun carregarRemedios(): List<Remedio> {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val json = prefs.getString(REMEDIOS_KEY, null) ?: return emptyList()

        val type = object : TypeToken<List<Remedio>>() {}.type
        return Gson().fromJson(json, type)
    }
}
