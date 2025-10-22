package com.haredultra.greenbookhaven.discover

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

class DiscoverBooksAdapter(private val books: List<Book>) : RecyclerView.Adapter<DiscoverBooksAdapter.DiscoverBookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.discover_book_item, parent, false)
        return DiscoverBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoverBookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class DiscoverBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookCoverImageView: ImageView = itemView.findViewById(R.id.book_cover_image_view)
        private val bookTitleTextView: TextView = itemView.findViewById(R.id.book_title_text_view)

        fun bind(book: Book) {
            bookCoverImageView.setImageResource(R.drawable.default_book_cover)
            bookTitleTextView.text = book.title

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, BookDetailsActivity::class.java)
                intent.putExtra("BOOK_ID", book.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}