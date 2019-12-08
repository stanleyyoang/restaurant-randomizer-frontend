package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.net.Uri;

import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import com.android.volley.*;
import com.android.volley.toolbox.Volley;

public class SearchRestaurant extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";
    public static final String RADIUS = "com.example.restaurantrandomizer.RADIUS";
    public static final String MIN_PRICE_2 = "com.example.restaurantrandomizer.MINPRICE2";
    public static final String MAX_PRICE_2 = "com.example.restaurantrandomizer.MAXPRICE2";

    // url ip address refers to host machine, cannot use localhost
    public static final String url = "http://10.0.2.2:8000/ping/";

    public JSONObject restaurant;
    public JSONObject jsonRequest;

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
        String minRating = extras.getString(IndicateRatingInterval.MIN_PRICE_2);
        String maxRating = extras.getString(IndicateRatingInterval.MAX_PRICE_2);

        RequestQueue queue = Volley.newRequestQueue(this);

        //request goes here
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        restaurant = response;
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                });

        queue.add(req);

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
