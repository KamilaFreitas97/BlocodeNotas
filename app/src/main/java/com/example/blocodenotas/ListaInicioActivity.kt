package com.example.blocodenotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenotas.model.Nota
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class ListaInicioActivity : AppCompatActivity() {
    val notasService = NotasService()
    lateinit var listNotas: ArrayList<Nota>
    lateinit var adapter: BlocoNotasAdapter
    lateinit var adicionarNotaLanucher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_inicio)
        supportActionBar?.hide()

        val recyclerView: RecyclerView = findViewById(R.id.listainicio)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adicionarNotaLanucher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //adicionando ou editando nota retornada da outra atividade
                if (it.resultCode == RESULT_OK) {
                    val nota = it.data!!.getSerializableExtra("nota") as Nota
                    if (nota.id == null) {
                        addNota(nota)
                    } else {
                        atualizarNota(nota)
                    }
                    Snackbar.make(recyclerView, "Nota adicionado com sucesso", Snackbar.LENGTH_LONG)
                        .show()
                    checaListavazia()

                }
            }

        notasService.ListarNotas(
            { notas ->
                listNotas = notas
                adapter = BlocoNotasAdapter(listNotas, clickListener = this::notaClickListener)
                recyclerView.adapter = adapter
                checaListavazia()

            },
            this::erro
        )

        val fablista: View = findViewById(R.id.fablista)
        fablista.setOnClickListener {
            val intent = Intent(this, AdicionarNotaActivity::class.java)
            adicionarNotaLanucher.launch(intent)

        }
    }

    fun addNota(nota: Nota) {
        notasService.adicionarNota(nota,
            { id ->
                listNotas.add(nota)
                adapter.notifyItemInserted(listNotas.size - 1)

            },
            this::erro
        )

    }

    fun atualizarNota(nota: Nota) {

        notasService.atualizarNota(nota,
            {
                val index = listNotas.indexOfFirst { item -> item.id == nota.id }
                listNotas.set(index, nota)
                adapter.notifyItemChanged(index)
            },
            this::erro
        )
    }

    fun notaClickListener(nota: Nota) {
        val intent = Intent(this, AdicionarNotaActivity::class.java).apply {
            putExtra("nota", nota)

        }

        adicionarNotaLanucher.launch(intent)
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

    fun erro(e: Exception) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
    }

}