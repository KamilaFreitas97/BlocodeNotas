package com.example.blocodenotas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenotas.model.Nota

class BlocoNotasAdapter (

    private val items: List<Nota>,
    private val clickListener: (Nota) -> Unit

) : RecyclerView.Adapter<BlocoNotasAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        with(holder) {
            //
            val menu: ImageView = itemView.findViewById(com.example.blocodenotas.R.id.menu)
            val titulo: TextView = itemView.findViewById(com.example.blocodenotas.R.id.titulo)
            val descricao: TextView = itemView.findViewById(com.example.blocodenotas.R.id.descricao)


            titulo.text = item.titulo
            descricao.text = item.descricao

            itemView.setOnClickListener {
                clickListener(item)
            }

        }
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}
