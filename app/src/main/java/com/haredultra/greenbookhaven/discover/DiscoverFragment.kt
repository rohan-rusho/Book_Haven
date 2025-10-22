package com.haredultra.greenbookhaven.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haredultra.greenbookhaven.BookHavenApplication
import com.haredultra.greenbookhaven.R
import com.haredultra.greenbookhaven.data.Book

class DiscoverFragment : Fragment() {

    private lateinit var discoverRecyclerView: RecyclerView
    private lateinit var discoverBooksAdapter: DiscoverBooksAdapter
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_discover, container, false)
        discoverRecyclerView = view.findViewById(R.id.discover_recycler_view)
        searchView = view.findViewById(R.id.book_search_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = activity?.application as BookHavenApplication
        val allBooks = app.bookDataManager.getBooks()
        discoverBooksAdapter = DiscoverBooksAdapter(allBooks)
        discoverRecyclerView.adapter = discoverBooksAdapter
        discoverRecyclerView.layoutManager = GridLayoutManager(context, 2)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredBooks = allBooks.filter { book ->
                    book.title?.contains(newText ?: "", ignoreCase = true) == true ||
                    book.author?.contains(newText ?: "", ignoreCase = true) == true
                }
                discoverBooksAdapter = DiscoverBooksAdapter(filteredBooks)
                discoverRecyclerView.adapter = discoverBooksAdapter
                return true
            }
        })
    }
}