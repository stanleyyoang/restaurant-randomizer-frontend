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

public class IndicatePriceInterval extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicate_price_interval);

        Intent intent = getIntent();
        String cuisineSelected = intent.getStringExtra(MainActivity.CUISINE_SELECTED);
        TextView hiddenCuisine = findViewById(R.id.hiddenCuisinePrice);
        hiddenCuisine.setText(cuisineSelected);

        TextView indicatePrice = findViewById(R.id.indicatePrice);
        indicatePrice.setText(R.string.indicate_price_interval);

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangePriceSeekbar);

        // get min and max text view
        final TextView tvMin = (TextView) findViewById(R.id.textMin1);
        final TextView tvMax = (TextView) findViewById(R.id.textMax1);

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

    public void indicateRadius(View view){
        Intent intent = new Intent(this, IndicateRadiusInterval.class);
        Bundle extras = new Bundle();

        TextView hiddenCuisine = (TextView) findViewById(R.id.hiddenCuisinePrice);
        String cuisine = hiddenCuisine.getText().toString();

        TextView tvMinPrice = (TextView) findViewById(R.id.textMin1);
        String minPrice = tvMinPrice.getText().toString();

        TextView tvMaxPrice = (TextView) findViewById(R.id.textMax1);
        String maxPrice = tvMaxPrice.getText().toString();

        extras.putString(CUISINE_SELECTED, cuisine);
        extras.putString(MIN_PRICE, minPrice);
        extras.putString(MAX_PRICE, maxPrice);
        intent.putExtras(extras);

        startActivity(intent);
    }
}
