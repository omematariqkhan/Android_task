package com.example.logintask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Link to the XML elements
        EditText etEmail = findViewById(R.id.etRegEmail);
        EditText etPassword = findViewById(R.id.etRegPassword);
        EditText etConfirm = findViewById(R.id.etRegConfirm);
        Button btnCreate = findViewById(R.id.btnCreateAccount);

        // When user clicks the "Create Account" button
        btnCreate.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            String conf = etConfirm.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(conf)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save user credentials using Auth.java
            Auth.saveUser(this, email, pass);
            Toast.makeText(this, "Account created successfully! Please login.", Toast.LENGTH_SHORT).show();

            // Return to login screen
            finish();
        });
    }
}
