package com.example.appsaudemain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoencaAdapter(
    private val lista: MutableList<Doenca>,
    private val onItemRemoved: () -> Unit
) : RecyclerView.Adapter<DoencaAdapter.DoencaViewHolder>() {

    inner class DoencaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNomeDoenca)
        val tvRemedio: TextView = itemView.findViewById(R.id.tvRemedio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoencaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doenca, parent, false)
        return DoencaViewHolder(view)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: DoencaViewHolder, position: Int) {
        val doenca = lista[position]
        holder.tvNome.text = doenca.nomeDoenca
        holder.tvRemedio.text = doenca.remedio

        holder.itemView.setOnLongClickListener {
            lista.removeAt(position)
            notifyItemRemoved(position)
            onItemRemoved()
            true
        }
    }
}
