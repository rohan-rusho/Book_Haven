package com.haredultra.greenbookhaven.bookdetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.haredultra.greenbookhaven.R;
import com.haredultra.greenbookhaven.data.Review;

import java.util.List;

public class BookReviewsAdapter extends RecyclerView.Adapter<BookReviewsAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    public BookReviewsAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        ImageView userAvatarImageView;
        TextView userNameTextView;
        RatingBar ratingBar;
        TextView reviewCommentTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatarImageView = itemView.findViewById(R.id.user_avatar_image_view);
            userNameTextView = itemView.findViewById(R.id.user_name_text_view);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            reviewCommentTextView = itemView.findViewById(R.id.review_comment_text_view);
        }

        public void bind(Review review) {
            // No user system, so display a generic user name
            userNameTextView.setText("User " + review.getUserId());
            ratingBar.setRating(review.getRating());
            reviewCommentTextView.setText(review.getComment());
            userAvatarImageView.setImageResource(R.drawable.ic_person); // Set default avatar
        }
    }
}