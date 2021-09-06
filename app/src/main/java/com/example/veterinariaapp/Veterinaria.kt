package com.example.veterinariaapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "veterinarias")
class Veterinaria(

    val nombre:String,
    val apellido:String,
    val numero:String,
    val serie: String,
    val descripcion: String,
    val revisiones: String,
    val direccion: String,
    @PrimaryKey(autoGenerate = true)
    var idVeterinaria: Int = 0

) : Serializable