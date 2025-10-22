package com.haredultra.greenbookhaven.mybooks

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

class MyBooksAdapter(private val books: List<Book>) : RecyclerView.Adapter<MyBooksAdapter.MyBooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_books_item, parent, false)
        return MyBooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyBooksViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class MyBooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookCoverImageView: ImageView = itemView.findViewById(R.id.book_cover_image_view)
        private val bookTitleTextView: TextView = itemView.findViewById(R.id.book_title_text_view)
        private val bookAuthorTextView: TextView = itemView.findViewById(R.id.book_author_text_view)
        private val bookStatusTextView: TextView = itemView.findViewById(R.id.book_status_text_view)

        fun bind(book: Book) {
            bookCoverImageView.setImageResource(R.drawable.default_book_cover)
            bookTitleTextView.text = book.title
            bookAuthorTextView.text = book.author
            bookStatusTextView.text = "Reviewed"

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, BookDetailsActivity::class.java)
                intent.putExtra("BOOK_ID", book.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}