package com.example.application_3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey()
    val dni: Int,
    val nombre: String,
    val email: String,
    val lenguaje: String


) : Serializable