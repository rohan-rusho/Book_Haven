package com.haredultra.greenbookhaven.writereview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haredultra.greenbookhaven.BookHavenApplication
import com.haredultra.greenbookhaven.R
import com.haredultra.greenbookhaven.data.Book
import com.haredultra.greenbookhaven.data.Review

class WriteReviewFragment : Fragment() {

    private lateinit var bookTitleEditText: EditText
    private lateinit var bookAuthorEditText: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var reviewEditText: EditText
    private lateinit var submitReviewButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_write_review, container, false)
        bookTitleEditText = view.findViewById(R.id.book_title_edit_text)
        bookAuthorEditText = view.findViewById(R.id.book_author_edit_text)
        ratingBar = view.findViewById(R.id.rating_bar)
        reviewEditText = view.findViewById(R.id.review_edit_text)
        submitReviewButton = view.findViewById(R.id.submit_review_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitReviewButton.setOnClickListener {
            val title = bookTitleEditText.text.toString()
            val author = bookAuthorEditText.text.toString()
            val rating = ratingBar.rating
            val reviewText = reviewEditText.text.toString()

            if (title.isNotEmpty() && author.isNotEmpty() && reviewText.isNotEmpty()) {
                val app = activity?.application as BookHavenApplication
                val newBook = Book(
                    id = (app.bookDataManager.getBooks().size + 1).toString(),
                    title = title,
                    author = author,
                    coverUrl = "",
                    description = reviewText,
                    genres = emptyList(),
                    averageRating = rating
                )

                val newReview = Review(
                    id = (app.bookDataManager.getReviews().size + 1).toString(),
                    bookId = newBook.id,
                    userId = "1", // Dummy user ID
                    rating = rating,
                    comment = reviewText
                )

                app.bookDataManager.addBook(newBook)
                app.bookDataManager.addReview(newReview)

                Toast.makeText(context, "Book added!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}