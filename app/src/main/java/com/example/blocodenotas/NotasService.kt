package com.example.blocodenotas

import com.example.blocodenotas.model.Nota
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotasService {
    private val database = Firebase.firestore

    fun ListarNotas(
        //passando função e que nao recebe nada
        cb: (notas: ArrayList<Nota>) -> Unit, err: (e: Exception) -> Unit
    ) {
        database.collection("notas").get().addOnSuccessListener { colecao ->
            val notas = ArrayList<Nota>()

            for (item in colecao) {
                val nota =
                    Nota(item.data.get("titulo") as String, item.data.get("descricao") as String)
                nota.id = item.id
                notas.add(nota)
            }
            cb(notas)
        }
            .addOnFailureListener(err)
    }
    fun adicionarNota(
        nota:Nota,
        cb: (id:String) -> Unit, err: (e: Exception) -> Unit
    )
    {
        database.collection("notas").add(nota).addOnSuccessListener { item ->
            cb(item.id)

        }
            .addOnFailureListener(err)
    }
    fun atualizarNota(
        nota:Nota,
        cb: () -> Unit, err: (e: Exception) -> Unit
    )
    {
        val map = HashMap<String, Any>()
        map.put("titulo", nota.titulo)
        map.put("descricao", nota.descricao)
        database.collection("notas").document(nota.id!!).update(map)
            .addOnSuccessListener {
                cb()
            }
            .addOnFailureListener(err)
    }

}