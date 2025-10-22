package com.haredultra.greenbookhaven.mybooks
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.haredultra.greenbookhaven.BookHavenApplication
import com.haredultra.greenbookhaven.R
import com.haredultra.greenbookhaven.data.Book

class MyBooksFragment : Fragment() {

    private lateinit var myBooksRecyclerView: RecyclerView
    private lateinit var myBooksAdapter: MyBooksAdapter
    private lateinit var writeReviewButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_books, container, false)
        myBooksRecyclerView = view.findViewById(R.id.my_books_recycler_view)
        writeReviewButton = view.findViewById(R.id.write_review_button)
        return view
    }

    override fun onStart() {
        super.onStart()
        val app = activity?.application as BookHavenApplication
        
        // Get all reviewed books since there are no users
        val allReviews = app.bookDataManager.getReviews()
        val reviewedBookIds = allReviews.map { it.bookId }.toSet()
        val allBooks = app.bookDataManager.getBooks()
        val reviewedBooks = allBooks.filter { reviewedBookIds.contains(it.id) }

        myBooksAdapter = MyBooksAdapter(reviewedBooks)
        myBooksRecyclerView.adapter = myBooksAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myBooksRecyclerView.layoutManager = LinearLayoutManager(context)

        writeReviewButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_my_books_to_navigation_write_review)
        }
    }
}