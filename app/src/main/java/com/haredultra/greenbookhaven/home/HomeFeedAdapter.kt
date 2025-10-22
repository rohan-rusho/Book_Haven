package com.haredultra.greenbookhaven.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haredultra.greenbookhaven.R
import com.haredultra.greenbookhaven.bookdetails.BookDetailsActivity
import com.haredultra.greenbookhaven.data.Book

class HomeFeedAdapter(private val items: List<Book>) : RecyclerView.Adapter<HomeFeedAdapter.HomeFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_feed_item, parent, false)
        return HomeFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeFeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HomeFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookCoverImageView: ImageView = itemView.findViewById(R.id.book_cover_image_view)
        private val bookTitleTextView: TextView = itemView.findViewById(R.id.book_title_text_view)
        private val bookAuthorTextView: TextView = itemView.findViewById(R.id.book_author_text_view)
        private val reviewSnippetTextView: TextView = itemView.findViewById(R.id.review_snippet_text_view)

        fun bind(book: Book) {
            bookCoverImageView.setImageResource(R.drawable.default_book_cover)
            bookTitleTextView.text = book.title
            bookAuthorTextView.text = book.author
            reviewSnippetTextView.text = book.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, BookDetailsActivity::class.java)
                intent.putExtra("BOOK_ID", book.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}