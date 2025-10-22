package com.haredultra.greenbookhaven.data

data class Review(
    val id: String,
    val bookId: String,
    val userId: String,
    val rating: Float,
    val comment: String
)
