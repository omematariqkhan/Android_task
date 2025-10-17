package com.example.logintask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        TextView tvTarget = findViewById(R.id.tvResetTarget);
        EditText etNew = findViewById(R.id.etNewPassword);
        EditText etConfirm = findViewById(R.id.etConfirmNewPassword);
        Button btnReset = findViewById(R.id.btnResetNow);

        String email = getIntent().getStringExtra("email");
        tvTarget.setText("Resetting for: " + (email == null ? "-" : email));

        btnReset.setOnClickListener(v -> {
            String a = etNew.getText().toString().trim();
            String b = etConfirm.getText().toString().trim();

            if (a.isEmpty() || b.isEmpty()) {
                Toast.makeText(this, "Fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!a.equals(b)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email == null) {
                Toast.makeText(this, "No email provided", Toast.LENGTH_SHORT).show();
                return;
            }

            Auth.resetPassword(this, email, a);
            Toast.makeText(this, "Password updated. Please login.", Toast.LENGTH_SHORT).show();
            finish(); // back to previous screen
        });
    }
}
