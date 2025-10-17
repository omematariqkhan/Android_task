package com.example.logintask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // find the views from activity_home.xml
        TextView textOmema = findViewById(R.id.textOmema);
        Button btnLogout = findViewById(R.id.btnLogout);

        // get the email (if passed from login screen)
        String email = getIntent().getStringExtra("email");
        if (email != null) {
            textOmema.setText("Welcome, " + email + "!");
        }

        // handle logout click
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            // Clear all previous activities so user can’t go back by pressing back
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
