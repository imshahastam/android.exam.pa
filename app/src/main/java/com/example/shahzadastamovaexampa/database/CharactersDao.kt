package com.example.shahzadastamovaexampa.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface CharactersDao {

    @Query("SELECT * FROM onecharacter")
    fun getAll(): Observable<List<OneCharacter>>

    @Query("SELECT * FROM onecharacter WHERE id = :id")
    fun getById(id: Long?): Single<OneCharacter>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(charactersList: List<OneCharacter>)

    @Insert
    fun insert(oneCharacter: OneCharacter) : Completable
}