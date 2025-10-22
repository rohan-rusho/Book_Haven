package com.haredultra.greenbookhaven.data

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverUrl: String,
    val description: String,
    val genres: List<String>,
    val averageRating: Float
)