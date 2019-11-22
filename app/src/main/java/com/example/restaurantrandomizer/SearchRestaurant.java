package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.net.Uri;

public class SearchRestaurant extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";
    public static final String RADIUS = "com.example.restaurantrandomizer.RADIUS";
    public static final String MIN_PRICE_2 = "com.example.restaurantrandomizer.MINPRICE2";
    public static final String MAX_PRICE_2 = "com.example.restaurantrandomizer.MAXPRICE2";

    private ProgressBar nProgressBar;
    Button map;
    Button phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String cuisineSelected = extras.getString(IndicateRatingInterval.CUISINE_SELECTED);
        String minPrice = extras.getString(IndicateRatingInterval.MIN_PRICE);
        String maxPrice = extras.getString(IndicateRatingInterval.MAX_PRICE);
        String radius = extras.getString(IndicateRatingInterval.RADIUS);
        String minPrice2 = extras.getString(IndicateRatingInterval.MIN_PRICE_2);
        String maxPrice2 = extras.getString(IndicateRatingInterval.MAX_PRICE_2);


        nProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        map = (Button) findViewById(R.id.button5);
        phone = (Button) findViewById(R.id.button6);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=5712+82nd+St+Unit+114, Lubbock, TX 79424"));
                SearchRestaurant.this.startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8067949898"));
                startActivity(intent);
            }
        });

    }
}
