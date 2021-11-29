package com.example.blocodenotas

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.blocodenotas.R.layout.activity_main


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var ListaInicio: ListaInicioActivity
    lateinit var fab: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_main)

        val titulo = findViewById<EditText>(R.id.editTextTextMultiLine2)
        val descricao = findViewById<EditText>(R.id.editTextTextMultiLine)
        val id = intent.getIntExtra("id", -1)//
        if (id != -1){
            //setText usa outros id que
            titulo.setText(intent.getStringExtra("titulo")!!)
            descricao.setText(intent.getStringExtra("descricao")!!)
        }


        val fab: View = findViewById(R.id.fab)

        fab.setOnClickListener { View ->
            val intent = Intent(this, ListaInicioActivity::class.java).apply {
                putExtra("id", id)
                putExtra("titulo", titulo.text?.toString())
                putExtra("descricao", descricao.text?.toString())

            }

            setResult(RESULT_OK, intent)
            finish()
        }


        /*fun menu(view : View){


        }*/

    }


}