package com.example.labtask_implicitintents_70146302;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_NAME = "com.example.labtask_implicitintents_70146302.EXTRA_STUDENT_NAME";

    private EditText etName;
    private Button btnPage, btnCallOffice, btnBusStop, btnReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnPage = findViewById(R.id.btnPage);
        btnCallOffice = findViewById(R.id.btnCallOffice);
        btnBusStop = findViewById(R.id.btnBusStop);
        btnReport = findViewById(R.id.btnReport);

        // 1. Open Transport Page in Browser
        btnPage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.uol.edu.pk"));
            openIntent(this, intent);
        });

        // 2. Open Phone Dialer with Transport Office number
        btnCallOffice.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:04298765432"));
            openIntent(this, intent);
        });

        // 3. Show Bus Stop on Maps App
        btnBusStop.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Thokar+Niaz+Baig,+Lahore"));
            openIntent(this, intent);
        });

        // 4. Validate Name and Open ProblemActivity
        btnReport.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                etName.setError("Please enter your name");
            } else {
                Intent intent = new Intent(MainActivity.this, ActivityProblem.class);
                intent.putExtra(EXTRA_STUDENT_NAME, name);
                startActivity(intent);
            }
        });
    }
    public static void openIntent(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No app found for this action", Toast.LENGTH_SHORT).show();
        }
    }
}