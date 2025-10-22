package com.haredultra.greenbookhaven.bookdetails;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.haredultra.greenbookhaven.BookHavenApplication;
import com.haredultra.greenbookhaven.R;
import com.haredultra.greenbookhaven.data.Book;
import com.haredultra.greenbookhaven.data.Review;
import com.haredultra.greenbookhaven.writereview.WriteReviewActivity;

import java.util.List;
import java.util.stream.Collectors;

public class BookDetailsActivity extends AppCompatActivity {

    private BookReviewsAdapter adapter;
    private RecyclerView reviewsRecyclerView;
    private String bookId;

    private final ActivityResultLauncher<Intent> writeReviewLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Refresh the reviews
                    loadReviews();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookId = getIntent().getStringExtra("BOOK_ID");

        if (bookId == null) {
            finish();
            return;
        }

        BookHavenApplication app = (BookHavenApplication) getApplication();
        Book book = app.bookDataManager.getBooks().stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
        if (book == null) {
            finish();
            return;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(book.getTitle());
        }

        ImageView bookCoverImageView = findViewById(R.id.book_cover_image_view);
        TextView bookTitleTextView = findViewById(R.id.book_title_text_view);
        TextView bookAuthorTextView = findViewById(R.id.book_author_text_view);
        TextView bookDescriptionTextView = findViewById(R.id.book_description_text_view);
        reviewsRecyclerView = findViewById(R.id.reviews_recycler_view);
        Button writeReviewButton = findViewById(R.id.write_review_button);

        bookCoverImageView.setImageResource(R.drawable.ic_open_book);
        bookTitleTextView.setText(book.getTitle());
        bookAuthorTextView.setText(book.getAuthor());
        bookDescriptionTextView.setText(book.getDescription());

        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadReviews();

        writeReviewButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, WriteReviewActivity.class);
            intent.putExtra("BOOK_ID", bookId);
            writeReviewLauncher.launch(intent);
        });
    }

    private void loadReviews() {
        BookHavenApplication app = (BookHavenApplication) getApplication();
        List<Review> reviews = app.bookDataManager.getReviews().stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
        adapter = new BookReviewsAdapter(reviews);
        reviewsRecyclerView.setAdapter(adapter);
    }
}