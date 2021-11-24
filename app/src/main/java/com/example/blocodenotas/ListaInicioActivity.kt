package com.example.blocodenotas

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenotas.model.Notas
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ListaInicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_inicio)
        supportActionBar?.hide()
        val image: ImageView = findViewById(R.id.telainicio)

        val recyclerView : RecyclerView = findViewById(R.id.listainicio)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        /*val n1 = Notas(1, "Terminar Trab", "Terminar ate amanha o trab de mobile")

        val n2 = Notas(1, "Terminar Trab", "Terminar ate amanha o trab de mobile")

        val n3 = Notas(1, "Terminar Trab", "Terminar ate amanha o trab de mobile")

        val n4 = Notas(1, "Terminar Trab", "Terminar ate amanha o trab de mobile")

        val n5 = Notas(1, "Terminar Trab", "Terminar ate amanha o trab de mobile")*/

        //val listNotas = listOf(n1, n2, n3, n4, n5)
        val listNotas = listOf<Notas>()
        recyclerView.adapter = BlocoNotasAdapter(listNotas)

        //se a lista estiver vazia
       if(listNotas.isEmpty()){
            image.visibility =  View.VISIBLE

        }

        val fablista: View = findViewById(R.id.fablista)
        fablista.setOnClickListener{
                View  ->


            val intent = Intent(this, MainActivity::class.java ).apply{
                putExtra("id", 1)
            }
            startActivity(intent)
            finish()
        }


    }



}