package com.example.kenikape;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class menupage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupage);

        // Set click listeners for the cards
        findViewById(R.id.inventoryCard).setOnClickListener(v -> {
            startActivity(new Intent(menupage.this, inventorypage.class));
        });

        findViewById(R.id.deliveryCard).setOnClickListener(v -> {
            startActivity(new Intent(menupage.this, deliverypage.class));
        });

        findViewById(R.id.orderCard).setOnClickListener(v -> {
            startActivity(new Intent(menupage.this, orderpage.class));
        });


        findViewById(R.id.productCard).setOnClickListener(v -> {
            Intent intent = new Intent(menupage.this, productpage.class);
            startActivity(intent);
        });



        findViewById(R.id.logoutCard).setOnClickListener(v -> {
            showLogoutConfirmationDialog();
        });

    }

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Close the activity (Logout)
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss(); // Close the dialog if No is clicked
                })
                .setCancelable(false) // Prevent closing dialog by clicking outside
                .show();
    }
}
