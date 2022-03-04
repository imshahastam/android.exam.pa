package com.example.shahzadastamovaexampa.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OneCharacter::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

}