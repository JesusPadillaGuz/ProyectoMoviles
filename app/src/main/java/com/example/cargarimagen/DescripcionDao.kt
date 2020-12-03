package com.example.cargarimagen

import androidx.room.*

@Dao
interface DescripcionDao{
    //recuperar todos los datos de la BD
    //CAMBIOS QUE REALICE:
    //Todas las fun se reemplazan por suspend fun
    @Query(value = "SELECT * FROM Descripcion")
    suspend fun getAll(): List<Descripcion>

    //recuperar un renglon de la BD por su ID
    @Query(value = "SELECT * FROM Descripcion WHERE id= :id")
    suspend fun getById(id: Int): Descripcion

    //update a la BD
    @Update
    suspend fun update(descripcion: Descripcion)

    //insertar ala BD
    @Insert
    suspend fun insert(descripcion: Descripcion?)

    //intentando insertar solo la descripcion
   // @Query(value = "INSERT *  INTO Descipcion.descrip WHERE descrip= :descrip")

    //eliminar un renglon de la BD
    @Delete
    suspend  fun delete(descripcion: Descripcion)

}