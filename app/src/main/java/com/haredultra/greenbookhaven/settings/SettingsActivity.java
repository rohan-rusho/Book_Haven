package com.haredultra.greenbookhaven.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.haredultra.greenbookhaven.R;

public class SettingsActivity extends AppCompatActivity {

    private TextInputEditText nameEditText;
    private TextInputEditText bioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nameEditText = findViewById(R.id.name_edit_text);
        bioEditText = findViewById(R.id.bio_edit_text);
        Button saveSettingsButton = findViewById(R.id.save_settings_button);

        SharedPreferences prefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        nameEditText.setText(prefs.getString("user_name", ""));
        bioEditText.setText(prefs.getString("user_bio", ""));

        saveSettingsButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String bio = bioEditText.getText().toString();

            if (!name.isEmpty()) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("user_name", name);
                editor.putString("user_bio", bio);
                editor.apply();

                Toast.makeText(SettingsActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SettingsActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}