package com.example.shahzadastamovaexampa.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    @GET("character")
    fun getAllCharacters(): Observable<Response>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Long? ): Single<Item>

}