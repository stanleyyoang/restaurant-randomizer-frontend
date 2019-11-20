package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IndicateRadiusInterval extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicate_radius_interval);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String cuisineSelected = extras.getString(IndicatePriceInterval.CUISINE_SELECTED);
        String minPrice = extras.getString(IndicatePriceInterval.MIN_PRICE);
        String maxPrice = extras.getString(IndicatePriceInterval.MAX_PRICE);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(cuisineSelected);

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(minPrice);

        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText(maxPrice);
    }
}
