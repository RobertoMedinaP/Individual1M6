package com.example.individual1m6.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tablaJugadores")
data class Player (

    //@PrimaryKey(autoGenerate = true)
    //@NonNull
    //val idPlayer: Int,
    val apodo: String,
    val nombrecompleto: String,
    val edad: Int

        )