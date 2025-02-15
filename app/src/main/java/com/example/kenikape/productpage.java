package com.example.kenikape;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class productpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        // Find views by ID
        CardView coffee1 = findViewById(R.id.coffee1);
        CardView coffee2 = findViewById(R.id.coffee2);
        CardView coffee3 = findViewById(R.id.coffee3);
        CardView coffee4 = findViewById(R.id.coffee4);
        CardView coffee5 = findViewById(R.id.coffee5);
        CardView coffee6 = findViewById(R.id.coffee6);

        // Set click listeners to show dialog instead of opening a new page
        coffee1.setOnClickListener(v -> showProductDialog(R.drawable.coffee1, "Espresso", 250));
        coffee2.setOnClickListener(v -> showProductDialog(R.drawable.coffee2, "Latte", 300));
        coffee3.setOnClickListener(v -> showProductDialog(R.drawable.coffee3, "Cappuccino", 300));
        coffee4.setOnClickListener(v -> showProductDialog(R.drawable.coffee4, "Mocha", 200));
        coffee5.setOnClickListener(v -> showProductDialog(R.drawable.coffee5, "Americano", 250));
        coffee6.setOnClickListener(v -> showProductDialog(R.drawable.coffee6, "Macchiato", 265));
    }

    private void showProductDialog(int imageResId, String productName, int basePrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        builder.setView(dialogView);

        // Initialize views in dialog
        ImageView productImage = dialogView.findViewById(R.id.productImage);
        TextView productTitle = dialogView.findViewById(R.id.productTitle);
        TextView productPrice = dialogView.findViewById(R.id.productPrice);
        Button buttonMinus = dialogView.findViewById(R.id.buttonMinus);
        Button buttonPlus = dialogView.findViewById(R.id.buttonPlus);
        TextView textQuantity = dialogView.findViewById(R.id.textQuantity);
        Button addToCartButton = dialogView.findViewById(R.id.addToCartButton);
        Button buyNowButton = dialogView.findViewById(R.id.buyNowButton);
        Button closeButton = dialogView.findViewById(R.id.closeButton);

        // Set values
        productImage.setImageResource(imageResId);
        productTitle.setText(productName);

        // Initialize quantity and price
        final int[] quantity = {1};
        textQuantity.setText(String.valueOf(quantity[0]));
        productPrice.setText("₱" + basePrice); // Initial price

        // Update price based on quantity
        buttonMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                textQuantity.setText(String.valueOf(quantity[0]));
                productPrice.setText("₱" + (basePrice * quantity[0]));
            }
        });

        buttonPlus.setOnClickListener(v -> {
            quantity[0]++;
            textQuantity.setText(String.valueOf(quantity[0]));
            productPrice.setText("₱" + (basePrice * quantity[0]));
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Handle button actions
        addToCartButton.setOnClickListener(v -> {
            // Handle Add to Cart action
            dialog.dismiss();
        });

        buyNowButton.setOnClickListener(v -> {
            // Open Confirm Payment Page with product details
            Intent intent = new Intent(this, confirmpayment.class);
            intent.putExtra("productName", productName);
            intent.putExtra("quantity", quantity[0]);
            intent.putExtra("totalPrice", basePrice * quantity[0]);
            intent.putExtra("productImageResId", imageResId); // Automatically pass product image
            startActivity(intent);
            dialog.dismiss();
        });

        closeButton.setOnClickListener(v -> {
            // Close the dialog when clicked
            dialog.dismiss();
        });
    }
}
