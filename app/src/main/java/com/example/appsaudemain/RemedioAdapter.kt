package com.example.appsaudemain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RemedioAdapter(
    private val lista: MutableList<Remedio>,
    private val onItemRemoved: () -> Unit
) : RecyclerView.Adapter<RemedioAdapter.RemedioViewHolder>() {

    inner class RemedioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNome)
        val tvQtd: TextView = itemView.findViewById(R.id.tvQuantidade)
        val tvHorario: TextView = itemView.findViewById(R.id.tvHorario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemedioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_remedio, parent, false)
        return RemedioViewHolder(view)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: RemedioViewHolder, position: Int) {
        val rem = lista[position]
        holder.tvNome.text = rem.nome
        holder.tvQtd.text = "Qtd: ${rem.quantidade}"
        holder.tvHorario.text = "Horário: ${rem.horario}"

        holder.itemView.setOnLongClickListener {
            lista.removeAt(position)
            notifyItemRemoved(position)
            onItemRemoved()
            true
        }
    }
}
