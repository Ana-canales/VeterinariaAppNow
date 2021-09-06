package com.example.veterinariaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_veterinaria.*
import kotlinx.android.synthetic.main.item_veterinaria.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VeterinariaActivity : AppCompatActivity(){

    private lateinit var database: AppDatabase
    private lateinit var veterinaria: Veterinaria
    private lateinit var veterinariaLiveData: LiveData<Veterinaria>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veterinaria)

        database = AppDatabase.getDatabase(this)

        val idVeterinaria = intent.getIntExtra("id", 0)

        veterinariaLiveData = database.veterinarias().get(idVeterinaria)

        veterinariaLiveData.observe(this, Observer {
            veterinaria = it

            nombre_veterinaria.text = veterinaria.nombre
            apellido_veterinaria.text = veterinaria.apellido
            numero_veterinaria.text = veterinaria.numero
            serie_veterinaria.text = veterinaria.serie
            descripcion_veterinaria.text = veterinaria.descripcion
            revisiones_veterinaria.text = veterinaria.revisiones
            direccion_veterinaria.text = veterinaria.direccion

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.veterinaria_menu, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_item -> {
                val intent = Intent(this, NuevoVeterinariaActivity::class.java)
                intent.putExtra("veterinaria", veterinaria)
                startActivity(intent)
            }

            R.id.delete_item -> {

                veterinariaLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.veterinarias().delete(veterinaria)
                    this@VeterinariaActivity.finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
