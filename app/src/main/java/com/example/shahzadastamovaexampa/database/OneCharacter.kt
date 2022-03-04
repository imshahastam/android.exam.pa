package com.example.shahzadastamovaexampa.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OneCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val image: String?,
    val episode: String?
)