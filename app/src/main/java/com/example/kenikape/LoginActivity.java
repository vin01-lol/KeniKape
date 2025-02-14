package com.example.kenikape;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Set up window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the login button by ID
        Button btnLogin = findViewById(R.id.btnLogin);

        // Set an OnClickListener to handle the button click
        btnLogin.setOnClickListener(v -> {
            // Create an Intent to start MenuPageActivity
            Intent intent = new Intent(LoginActivity.this, menupage.class);
            startActivity(intent);  // Start the MenuPageActivity
            finish();  // Optional: Finish the LoginActivity to prevent going back to it
        });
    }
}
