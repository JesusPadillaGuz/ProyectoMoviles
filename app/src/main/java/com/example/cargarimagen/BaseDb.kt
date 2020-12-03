package com.example.cargarimagen

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Descripcion::class),
    version = 1
)
abstract class BaseDb: RoomDatabase() {
    abstract fun descripcionDao(): DescripcionDao
}