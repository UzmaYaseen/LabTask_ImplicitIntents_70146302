package com.example.labtask_implicitintents_70146302;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityProblem extends AppCompatActivity {

    private TextView tvGreeting;
    private EditText etDriverPhone, etEmail;
    private Button btnCallDriver, btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        tvGreeting = findViewById(R.id.tvGreeting);
        etDriverPhone = findViewById(R.id.etDriverPhone);
        etEmail = findViewById(R.id.etEmail);
        btnCallDriver = findViewById(R.id.btnCallDriver);
        btnSendEmail = findViewById(R.id.btnSendEmail);

        // Receive Student Name
        String name = getIntent().getStringExtra(MainActivity.EXTRA_STUDENT_NAME);
        if (name == null || name.trim().isEmpty()) {
            name = "Student";
        }
        tvGreeting.setText("Hello, " + name + "! Tell us what went wrong.");

        // Validate Driver Phone (11 digits starting with 03)
        btnCallDriver.setOnClickListener(v -> {
            String phone = etDriverPhone.getText().toString().trim();
            if (phone.matches("^03\\d{9}$")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                MainActivity.openIntent(this, intent);
            } else {
                etDriverPhone.setError("Enter an 11-digit number starting with 03");
            }
        });

        // Validate Email Address
        btnSendEmail.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                MainActivity.openIntent(this, intent);
            } else {
                etEmail.setError("Please enter a valid email address");
            }
        });
    }
}