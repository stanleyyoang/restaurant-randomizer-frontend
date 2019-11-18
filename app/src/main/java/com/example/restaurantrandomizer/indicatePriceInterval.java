package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class indicatePriceInterval extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicate_price_interval);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.CUISINE_SELECTED);

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}
