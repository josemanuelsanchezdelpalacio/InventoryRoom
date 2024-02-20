package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//lista entities de la clase Item, exportSchema false para no guardar las copias del historial de versiones
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    //permite el acceso a los métodos para crear u obtener la base de datos y uso el nombre de clase como calificador
    companion object {
        //los cambios realizados por un subproceso en Instance son visibles de inmediato para todos los demás subprocesos
        @Volatile
        private var Instance: InventoryDatabase? = null

        //si Instance no es nula, devuelve it, si lo es crea una nueva instancia de la base de datos
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
