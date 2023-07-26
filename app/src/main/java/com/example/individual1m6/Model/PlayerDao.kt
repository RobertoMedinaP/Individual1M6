package com.example.individual1m6.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PlayerDao {

    //necesito agregar nuevo player,eliminar usuario, mostrar todos los usuarios


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)

    @Query("SELECT * FROM tablaJugadores")
    fun showallplayers(): List<Player>

    //TODO revisar las funciones del DAO
}