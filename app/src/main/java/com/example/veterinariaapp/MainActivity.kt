package com.example.veterinariaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaVeterinarias = emptyList<Veterinaria>()

        val database = AppDatabase.getDatabase(this)

        database.veterinarias().getAll().observe(this, Observer {
            listaVeterinarias = it

            val adapter = VeterinariaAdapter(this, listaVeterinarias)

            lista.adapter = adapter
        })

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, VeterinariaActivity::class.java)
            intent.putExtra("id", listaVeterinarias[position].idVeterinaria)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NuevoVeterinariaActivity::class.java)
            startActivity(intent)
        }
    }
}