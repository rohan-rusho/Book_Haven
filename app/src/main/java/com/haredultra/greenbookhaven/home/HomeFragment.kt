package com.haredultra.greenbookhaven.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haredultra.greenbookhaven.BookHavenApplication
import com.haredultra.greenbookhaven.R

class HomeFragment : Fragment() {

    private lateinit var homeFeedRecyclerView: RecyclerView
    private lateinit var homeFeedAdapter: HomeFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeFeedRecyclerView = view.findViewById(R.id.home_feed_recycler_view)
        return view
    }

    override fun onStart() {
        super.onStart()
        val app = activity?.application as BookHavenApplication
        val books = app.bookDataManager.getBooks()
        homeFeedAdapter = HomeFeedAdapter(books)
        homeFeedRecyclerView.adapter = homeFeedAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFeedRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}