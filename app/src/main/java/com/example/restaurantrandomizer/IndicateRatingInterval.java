package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

public class IndicateRatingInterval extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";
    public static final String RADIUS = "com.example.restaurantrandomizer.RADIUS";
    public static final String MIN_PRICE_2 = "com.example.restaurantrandomizer.MINPRICE2";
    public static final String MAX_PRICE_2 = "com.example.restaurantrandomizer.MAXPRICE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicate_rating_interval);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String cuisineSelected = extras.getString(IndicateRadiusInterval.CUISINE_SELECTED);
        String minPrice = extras.getString(IndicateRadiusInterval.MIN_PRICE);
        String maxPrice = extras.getString(IndicateRadiusInterval.MAX_PRICE);
        String radius = extras.getString(IndicateRadiusInterval.RADIUS);

        TextView hiddenCuisine = findViewById(R.id.hiddenCuisineRating);
        hiddenCuisine.setText(cuisineSelected);

        TextView hiddenMinPrice = findViewById(R.id.hiddenMinPriceRating);
        hiddenMinPrice.setText(minPrice);

        TextView hiddenMaxPrice = findViewById(R.id.hiddenMaxPriceRating);
        hiddenMaxPrice.setText(maxPrice);

        TextView hiddenRadius = findViewById(R.id.hiddenRadiusRating);
        hiddenRadius.setText(radius);

        TextView indicateRating = findViewById(R.id.indicateRating);
        indicateRating.setText(R.string.indicate_rating_interval);

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangeRatingSeekbar);

        // get min and max text view
        final TextView tvMin = (TextView) findViewById(R.id.textMin2);
        final TextView tvMax = (TextView) findViewById(R.id.textMax2);

        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });
    }

    public void searchRestaurant(View view){

        Intent intent = new Intent(this, SearchRestaurant.class);
        Bundle extras = new Bundle();

        TextView hiddenCuisine = (TextView) findViewById(R.id.hiddenCuisineRating);
        String cuisine = hiddenCuisine.getText().toString();

        TextView tvMinPrice = (TextView) findViewById(R.id.hiddenMinPriceRating);
        String minPrice = tvMinPrice.getText().toString();

        TextView tvMaxPrice = (TextView) findViewById(R.id.hiddenMaxPriceRating);
        String maxPrice = tvMaxPrice.getText().toString();

        TextView hiddenRadius = (TextView) findViewById(R.id.hiddenRadiusRating);
        String radius = hiddenRadius.getText().toString();

        TextView tvMinPrice2 = (TextView) findViewById(R.id.textMin2);
        String minPrice2 = tvMinPrice2.getText().toString();

        TextView tvMaxPrice2 = (TextView) findViewById(R.id.textMax2);
        String maxPrice2 = tvMaxPrice2.getText().toString();

        extras.putString(CUISINE_SELECTED, cuisine);
        extras.putString(MIN_PRICE, minPrice);
        extras.putString(MAX_PRICE, maxPrice);
        extras.putString(RADIUS, radius);
        extras.putString(MIN_PRICE_2, minPrice2);
        extras.putString(MAX_PRICE_2, maxPrice2);
        intent.putExtras(extras);

        startActivity(intent);
    }
}
