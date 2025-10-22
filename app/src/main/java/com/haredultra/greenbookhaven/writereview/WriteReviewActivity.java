package com.haredultra.greenbookhaven.writereview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.haredultra.greenbookhaven.BookHavenApplication;
import com.haredultra.greenbookhaven.R;
import com.haredultra.greenbookhaven.data.Review;

public class WriteReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextInputEditText reviewEditText;
    private Button submitReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        ratingBar = findViewById(R.id.rating_bar);
        reviewEditText = findViewById(R.id.review_edit_text);
        submitReviewButton = findViewById(R.id.submit_review_button);

        String bookId = getIntent().getStringExtra("BOOK_ID");

        submitReviewButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String reviewText = reviewEditText.getText().toString();

            if (!reviewText.isEmpty()) {
                BookHavenApplication app = (BookHavenApplication) getApplication();
                Review newReview = new Review(
                        String.valueOf(app.bookDataManager.getReviews().size() + 1),
                        bookId,
                        "1", // Dummy user ID
                        rating,
                        reviewText
                );

                app.bookDataManager.addReview(newReview);
                Toast.makeText(this, "Review submitted!", Toast.LENGTH_SHORT).show();

                // Set the result and finish
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}