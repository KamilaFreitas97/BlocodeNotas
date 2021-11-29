package com.example.blocodenotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenotas.model.Notas
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class ListaInicioActivity : AppCompatActivity() {

    var listNotas: ArrayList<Notas> = arrayListOf<Notas>()
    lateinit var adapter: BlocoNotasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_inicio)
        supportActionBar?.hide()
        
        var id = 0

        val recyclerView: RecyclerView = findViewById(R.id.listainicio)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val adicionarNotaLanucher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

                if (it.resultCode == RESULT_OK) {

                    val idAtual = it.data!!.getIntExtra(
                        "id",
                        -1
                    ) //differenciar quando for adicionar ou editar
                    val titulo = it.data!!.getStringExtra("titulo")
                    val descricao = it.data!!.getStringExtra("descricao")
                    val notas = Notas(id, titulo!!, descricao!!)
                    if (idAtual == -1) {

                        listNotas.add(notas)
                        adapter.notifyItemInserted(listNotas.size - 1)
                        id++
                    } else {
                        //pegando a nota de idatual
                        val index = listNotas.indexOfFirst { nota -> nota.id == idAtual }
                        listNotas.set(index, notas)
                        adapter.notifyItemChanged(index)
                    }
                    Snackbar.make(recyclerView, "Nota adicionado com sucesso", Snackbar.LENGTH_LONG)
                        .show()
                    checaListavazia()

                }
            }
        adapter = BlocoNotasAdapter(listNotas, clickListener = { nota ->
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("id", nota.id)
                putExtra("titulo", nota.titulo)
                putExtra("descricao", nota.descricao)

            }

            adicionarNotaLanucher.launch(intent)
        })

        recyclerView.adapter = adapter

        val fablista: View = findViewById(R.id.fablista)
        fablista.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            adicionarNotaLanucher.launch(intent)

        }

        checaListavazia()

    }


    fun checaListavazia() {
        val image: ImageView = findViewById(R.id.telainicio)
        //se a lista estiver vazia
        if (listNotas.isEmpty()) {
            image.visibility = View.VISIBLE

        } else {
            image.visibility = View.INVISIBLE
        }

    }

}