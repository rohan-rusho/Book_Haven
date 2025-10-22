package com.haredultra.greenbookhaven.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haredultra.greenbookhaven.BookHavenApplication
import com.haredultra.greenbookhaven.R
import com.haredultra.greenbookhaven.data.Review

class ProfileReviewsAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ProfileReviewsAdapter.ProfileReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_review_item, parent, false)
        return ProfileReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount(): Int = reviews.size

    class ProfileReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookCoverImageView: ImageView = itemView.findViewById(R.id.book_cover_image_view)
        private val bookTitleTextView: TextView = itemView.findViewById(R.id.book_title_text_view)
        private val reviewCommentTextView: TextView = itemView.findViewById(R.id.review_comment_text_view)

        fun bind(review: Review) {
            val app = itemView.context.applicationContext as BookHavenApplication
            val book = app.bookDataManager.getBooks().find { it.id == review.bookId }

            bookCoverImageView.setImageResource(R.drawable.default_book_cover)
            bookTitleTextView.text = book?.title ?: "Unknown Book"
            reviewCommentTextView.text = review.comment
        }
    }
}