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
import com.example.blocodenotas.R.layout.activity_main


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var ListaInicio : ListaInicioActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_main)


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener{
                View  -> Snackbar.make(View, "", Snackbar.LENGTH_LONG)
            .setAction("Nota adicionado com sucesso",null).show()

            val intent = Intent(this, ListaInicioActivity::class.java ).apply{
                putExtra("id", 1)
            }
            startActivity(intent)
            finish()
        }

        /*fun menu(view : View){


        }*/

    }


}