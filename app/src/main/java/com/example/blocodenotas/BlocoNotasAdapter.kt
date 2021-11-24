package com.example.blocodenotas

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenotas.model.Notas

class BlocoNotasAdapter (

    private val items: List<Notas>
) : RecyclerView.Adapter<BlocoNotasAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Notas) {
            with(itemView) {
                //val img_pokemon : ImageView = findViewById(R.id.img_pokemon)
                val menu: ImageView = findViewById(R.id.menu)
                val titulo: TextView = findViewById(R.id.titulo)
                val descricao: TextView = findViewById(R.id.descrição)


                itemView.setOnClickListener {
                    //aqui é para aparecer o menu
                }


            }
        }
    }
}
