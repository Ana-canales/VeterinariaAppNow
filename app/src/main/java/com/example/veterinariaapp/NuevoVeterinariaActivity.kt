package com.example.veterinariaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_veterinaria.*
import kotlinx.android.synthetic.main.item_veterinaria.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoVeterinariaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_veterinaria)

        var idVeterinaria: Int? = null

        if (intent.hasExtra("veterinaria")) {
            val veterinaria = intent.extras?.getSerializable("veterinaria") as Veterinaria

            nombre_veti.setText(veterinaria.nombre)
            apellido_veti.setText(veterinaria.apellido)
            numero_veti.setText(veterinaria.numero)
            serie_veti.setText(veterinaria.serie)
            descripcion_veti.setText(veterinaria.descripcion)
            revisiones_veti.setText(veterinaria.revisiones)
            direccion_veti.setText(veterinaria.direccion)
        }

        val database = AppDatabase.getDatabase(this)

        save_btn.setOnClickListener {
            val nombre = nombre_veti.text.toString()
            val apellido = apellido_veti.text.toString()
            val numero = numero_veti.text.toString()
            val serie = serie_veti.text.toString()
            val descripcion = descripcion_veti.text.toString()
            val revisiones = revisiones_veti.text.toString()
            val direccion = direccion_veti.text.toString()

            val veterinaria = Veterinaria(nombre, apellido, numero, serie, descripcion, revisiones, direccion)

            if (idVeterinaria!= null) {
                CoroutineScope(Dispatchers.IO).launch {
                    veterinaria.idVeterinaria = idVeterinaria

                    database.veterinarias().update(veterinaria)

                    this@NuevoVeterinariaActivity.finish()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.veterinarias().insertAll(veterinaria)

                    this@NuevoVeterinariaActivity.finish()
                }
            }
        }
    }
}