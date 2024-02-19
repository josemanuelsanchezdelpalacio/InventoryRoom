package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface ItemDao {
    //en caso de conflicto ignora el item
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //suspend para permitir que se ejecute en un subproceso separado
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)
}