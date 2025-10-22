package com.haredultra.greenbookhaven.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookDataManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("BookHavenData", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getBooks(): MutableList<Book> {
        val json = sharedPreferences.getString("books", null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Book>>() {}.type
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    fun getReviews(): MutableList<Review> {
        val json = sharedPreferences.getString("reviews", null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<Review>>() {}.type
            gson.fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    fun saveBooks(books: List<Book>) {
        val json = gson.toJson(books)
        sharedPreferences.edit().putString("books", json).apply()
    }

    fun saveReviews(reviews: List<Review>) {
        val json = gson.toJson(reviews)
        sharedPreferences.edit().putString("reviews", json).apply()
    }

    fun addBook(book: Book) {
        val books = getBooks()
        books.add(book)
        saveBooks(books)
    }

    fun addReview(review: Review) {
        val reviews = getReviews()
        reviews.add(review)
        saveReviews(reviews)
    }

    fun getBooksReviewedByUser(userId: String): List<Book> {
        val reviews = getReviews()
        val reviewedBookIds = reviews.filter { it.userId == userId }.map { it.bookId }.toSet()
        val allBooks = getBooks()
        return allBooks.filter { reviewedBookIds.contains(it.id) }
    }

    private fun isDataPrepopulated(): Boolean {
        return sharedPreferences.getBoolean("data_prepopulated", false)
    }

    private fun setDataPrepopulated() {
        sharedPreferences.edit().putBoolean("data_prepopulated", true).apply()
    }

    fun prePopulateData() {
        if (!isDataPrepopulated()) {
            val initialBooks = mutableListOf(
                Book("1", "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "", "A hilarious sci-fi adventure.", listOf("Sci-Fi", "Comedy"), 4.5f),
                Book("2", "The Lord of the Rings", "J.R.R. Tolkien", "", "An epic fantasy journey.", listOf("Fantasy"), 4.8f),
                Book("3", "Pride and Prejudice", "Jane Austen", "", "A classic romance novel.", listOf("Romance"), 4.7f),
                Book("4", "To Kill a Mockingbird", "Harper Lee", "", "A powerful story about justice.", listOf("Fiction"), 4.6f),
                Book("5", "1984", "George Orwell", "", "A dystopian masterpiece.", listOf("Dystopian"), 4.9f),
                Book("6", "The Great Gatsby", "F. Scott Fitzgerald", "", "A story of the American dream.", listOf("Classic"), 4.3f),
                Book("7", "Dune", "Frank Herbert", "", "A landmark of science fiction.", listOf("Sci-Fi"), 4.8f),
                Book("8", "The Catcher in the Rye", "J.D. Salinger", "", "A story about teenage angst.", listOf("Classic"), 4.1f),
                Book("9", "Brave New World", "Aldous Huxley", "", "A chilling vision of the future.", listOf("Dystopian"), 4.7f),
                Book("10", "The Hobbit", "J.R.R. Tolkien", "", "A charming adventure.", listOf("Fantasy"), 4.6f),
                Book("11", "Fahrenheit 451", "Ray Bradbury", "", "A future where books are banned.", listOf("Dystopian"), 4.5f),
                Book("12", "Moby Dick", "Herman Melville", "", "A tale of obsession.", listOf("Classic"), 3.9f),
                Book("13", "War and Peace", "Leo Tolstoy", "", "An epic historical novel.", listOf("Classic"), 4.2f),
                Book("14", "The Chronicles of Narnia", "C.S. Lewis", "", "A magical world awaits.", listOf("Fantasy"), 4.6f),
                Book("15", "Animal Farm", "George Orwell", "", "A political allegory.", listOf("Classic"), 4.4f),
                Book("16", "Gone with the Wind", "Margaret Mitchell", "", "A historical romance.", listOf("Classic"), 4.3f),
                Book("17", "The Alchemist", "Paulo Coelho", "", "A journey of self-discovery.", listOf("Fantasy"), 4.4f),
                Book("18", "Jane Eyre", "Charlotte Brontë", "", "A classic gothic romance.", listOf("Romance"), 4.5f),
                Book("19", "Wuthering Heights", "Emily Brontë", "", "A dark and passionate story.", listOf("Romance"), 4.1f),
                Book("20", "Frankenstein", "Mary Shelley", "", "A monster and his creator.", listOf("Gothic"), 4.3f)
            )
            saveBooks(initialBooks)

            // Start with an empty list of reviews, as requested.
            saveReviews(mutableListOf())

            setDataPrepopulated()
        }
    }
}