package com.example.blocodenotas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.blocodenotas.R.layout.activity_adicionar_nota
import com.example.blocodenotas.model.Nota


class AdicionarNotaActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var ListaInicio: ListaInicioActivity
    lateinit var fab: Button
    var notaInicial : Nota? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_adicionar_nota)

        val tituloView = findViewById<EditText>(R.id.editTextTextMultiLine2)
        val descricaoView = findViewById<EditText>(R.id.editTextTextMultiLine)
        val notaInicial = intent.getSerializableExtra("nota") as Nota?
        if (notaInicial != null) {

            tituloView.setText(notaInicial.titulo)
            descricaoView.setText(notaInicial.descricao)
        }


        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener { View ->
            val nota = Nota(tituloView.text.toString(), descricaoView.text.toString())
            if(notaInicial != null){
                nota.id = notaInicial.id
            }
            val intent = Intent(this, ListaInicioActivity::class.java).apply {
                putExtra("nota", nota)
            }

            setResult(RESULT_OK, intent)
            finish()
        }

    }


}