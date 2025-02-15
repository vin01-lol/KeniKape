package com.example.kenikape;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class productdialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpage);

        // Find views by ID
        CardView coffee1 = findViewById(R.id.coffee1);
        coffee1.setOnClickListener(v -> showProductDialog(R.drawable.coffee1, "Espresso", "$5.00"));
    }

    // The method should be inside the class, not outside
    private void showProductDialog(int imageResId, String productName, String price) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_productpage, null);
        builder.setView(dialogView);

        ImageView productImage = dialogView.findViewById(R.id.productImage);
        TextView productTitle = dialogView.findViewById(R.id.productTitle);
        TextView productPrice = dialogView.findViewById(R.id.productPrice);

        Button addToCartButton = dialogView.findViewById(R.id.addToCartButton);
        Button buyNowButton = dialogView.findViewById(R.id.buyNowButton);

        productImage.setImageResource(imageResId);
        productTitle.setText(productName);
        productPrice.setText(price);

        AlertDialog dialog = builder.create();
        dialog.show();

        addToCartButton.setOnClickListener(v -> {
            // Add to cart logic
            dialog.dismiss();
        });

        buyNowButton.setOnClickListener(v -> {
            // Buy now logic
            dialog.dismiss();
        });
    }
}
