package com.example.shahzadastamovaexampa.api

data class Response(
    val results: List<Item>
)

data class Item(
    val id: Long?,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val image: String?,
    val episode: List<String>?
)
