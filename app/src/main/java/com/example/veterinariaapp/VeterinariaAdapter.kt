package com.example.veterinariaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_veterinaria.view.*

class VeterinariaAdapter(private val mContext: Context, private val listaveterinaria: List<Veterinaria>) :
    ArrayAdapter<Veterinaria>(mContext, 0, listaveterinaria) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_veterinaria,parent,false)

        val veterinaria = listaveterinaria[position]

        layout.nombre_vet.text = veterinaria.nombre
        layout.apellido_vet.text = veterinaria.apellido
        layout.numero_vet.text = veterinaria.numero
        //   layout.serie_vet.text = veterinaria.serie
        //  layout.descripcion_vet.text = veterinaria.descripcion
        // layout.revisiones_vet.text = veterinaria.revisiones //
        // layout.direccion_vet.text = veterinaria.direccion



        return layout
    }
}