package com.example.veterinariaapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VeterinariasDao {
    @Query("SELECT * FROM veterinarias")
    fun getAll(): LiveData<List<Veterinaria>>

    @Query("SELECT * FROM veterinarias WHERE idVeterinaria = :id")
    fun get(id: Int): LiveData<Veterinaria>

    @Insert
    fun insertAll(vararg veterinarias: Veterinaria)

    @Update
    fun update(veterinaria: Veterinaria)

    @Delete
    fun delete(veterinaria: Veterinaria)
}