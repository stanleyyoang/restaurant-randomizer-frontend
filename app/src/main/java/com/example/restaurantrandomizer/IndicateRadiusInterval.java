package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class IndicateRadiusInterval extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";
    public static final String RADIUS = "com.example.restaurantrandomizer.RADIUS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicate_radius_interval);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String cuisineSelected = extras.getString(IndicatePriceInterval.CUISINE_SELECTED);
        String minPrice = extras.getString(IndicatePriceInterval.MIN_PRICE);
        String maxPrice = extras.getString(IndicatePriceInterval.MAX_PRICE);

        TextView hiddenCuisine = findViewById(R.id.hiddenCuisineRadius);
        hiddenCuisine.setText(cuisineSelected);

        TextView hiddenMinPrice = findViewById(R.id.hiddenMinPriceRadius);
        hiddenMinPrice.setText(minPrice);

        TextView hiddenMaxPrice = findViewById(R.id.hiddenMaxPriceRadius);
        hiddenMaxPrice.setText(maxPrice);

        TextView indicateRadius = findViewById(R.id.indicateRadius);
        indicateRadius.setText(R.string.indicate_max_radius);

        Spinner spinner = (Spinner) findViewById(R.id.radiusSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.radius, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void indicateRating(View view) {
        Intent intent = new Intent(this, IndicateRatingInterval.class);
        Bundle extras = new Bundle();

        TextView hiddenCuisine = (TextView) findViewById(R.id.hiddenCuisineRadius);
        String cuisine = hiddenCuisine.getText().toString();

        TextView tvMinPrice = (TextView) findViewById(R.id.hiddenMinPriceRadius);
        String minPrice = tvMinPrice.getText().toString();

        TextView tvMaxPrice = (TextView) findViewById(R.id.hiddenMaxPriceRadius);
        String maxPrice = tvMaxPrice.getText().toString();

        Spinner radiusSpinner = (Spinner) findViewById(R.id.radiusSpinner);
        String radius = radiusSpinner.getSelectedItem().toString();

        extras.putString(CUISINE_SELECTED, cuisine);
        extras.putString(MIN_PRICE, minPrice);
        extras.putString(MAX_PRICE, maxPrice);
        extras.putString(RADIUS, radius);
        intent.putExtras(extras);

        startActivity(intent);
    }
}
