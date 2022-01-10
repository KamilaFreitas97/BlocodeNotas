package com.example.blocodenotas.model

import java.io.Serializable

data class Nota(
    val titulo: String,
    val descricao: String,


    )
    :Serializable
{
    var id: String? = null
}