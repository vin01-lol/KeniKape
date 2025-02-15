package com.example.kenikape;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirmpayment extends AppCompatActivity {

    private String selectedPaymentMethod = "Cash"; // Default payment method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpayment);

        // Get data from Intent
        String productName = getIntent().getStringExtra("productName");
        int quantity = getIntent().getIntExtra("quantity", 1);
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);
        int productImageResId = getIntent().getIntExtra("productImageResId", R.drawable.confirm); // Default image

        // Find views
        ImageView productImage = findViewById(R.id.productImage);
        TextView confirmText = findViewById(R.id.confirmText);
        Button confirmButton = findViewById(R.id.confirmButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        RadioGroup paymentOptions = findViewById(R.id.paymentOptions);
        RadioButton cashOption = findViewById(R.id.cashOption);
        RadioButton gcashOption = findViewById(R.id.gcashOption);

        // Set values
        productImage.setImageResource(productImageResId);
        confirmText.setText(productName + "\nQuantity: " + quantity + "\nTotal: ₱" + totalPrice);

        // Payment method selection
        paymentOptions.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.cashOption) {
                selectedPaymentMethod = "Cash";
            } else if (checkedId == R.id.gcashOption) {
                selectedPaymentMethod = "GCash";
            }
        });

        // Confirm button (Show Payment Successful Dialog)
        confirmButton.setOnClickListener(v -> showPaymentConfirmation());

        // Cancel button (Go back)
        cancelButton.setOnClickListener(v -> finish());
    }

    private void showPaymentConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Payment");
        builder.setMessage("You have chosen " + selectedPaymentMethod + " as your payment method.\nDo you want to proceed?");
        builder.setIcon(R.drawable.confirm);
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (dialog, which) -> {
            dialog.dismiss();
            showSuccessDialog();
        });

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Successful");
        builder.setMessage("Thank you for your purchase using " + selectedPaymentMethod + "!");
        builder.setIcon(R.drawable.confirm);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            finish();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Automatically close the dialog after 3 seconds
        new Handler().postDelayed(() -> {
            dialog.dismiss();
            finish();
        }, 5000);
    }
}
