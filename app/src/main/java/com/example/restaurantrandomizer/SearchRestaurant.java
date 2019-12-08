package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import com.android.volley.*;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchRestaurant extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    public static final String MIN_PRICE = "com.example.restaurantrandomizer.MINPRICE";
    public static final String MAX_PRICE = "com.example.restaurantrandomizer.MAXPRICE";
    public static final String RADIUS = "com.example.restaurantrandomizer.RADIUS";
    public static final String MIN_PRICE_2 = "com.example.restaurantrandomizer.MINPRICE2";
    public static final String MAX_PRICE_2 = "com.example.restaurantrandomizer.MAXPRICE2";
    // url ip address refers to host machine, cannot use localhost
    //public static final String url = "http://10.0.2.2:8000/findfood";
    //Testing url
    public static final String url = "localhost:8000/findfood";

    public JSONObject restaurant;

    private ProgressBar nProgressBar;
    Button map;
    Button phone;

    private FusedLocationProviderClient locationClient;
    private LocationRequest mLocationRequest;

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
        final String[] coords = new String[2]; //idk why you have to do it like this, but you do.

        locationClient = LocationServices.getFusedLocationProviderClient(this);

        checkLocationSettings();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(100);
        //location request
        locationClient.requestLocationUpdates(mLocationRequest,
                new LocationCallback() {
                    public void onLocationResult(LocationResult result) {
                        Location location = result.getLastLocation();
                        coords[0] = Double.toString(location.getLongitude());
                        coords[1] = Double.toString(location.getLatitude());
                    }
                    public void onLocationAvailability(LocationAvailability lA){
                        return;
                    }
                }, Looper.myLooper());

        //Encoding the url
        String temp = url + "?cuisine=" + cuisineSelected + "&minPrice=" + minPrice + "&maxPrice=" +
                maxPrice + "&radius=" + radius + "&lon=" + coords[0] + "&lat=" + coords[1] + "&minRating=" +
                minRating + "&maxRating=" + maxRating;
        String GET_URL = "";
        try {
            GET_URL = URLEncoder.encode(temp, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            Toast.makeText(this, "URL encoding failed!\n" + temp, Toast.LENGTH_LONG).show();
        }
        //JSON request
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();
        JsonObjectRequest req = new JsonObjectRequest
                (Request.Method.GET, GET_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        restaurant = response;
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        restaurant = null;
                        Log.d("Error.Response", error.toString());
                    }
                });
        queue.add(req); //add request to queue for dispatch
        
        nProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        map = (Button) findViewById(R.id.button5);
        phone = (Button) findViewById(R.id.button6);

        //unpack the restaurant JSON
        String address;
        String phoneNum;
        try {
            //JSONObject object = restaurant.getJSONObject("results");
            address = restaurant.getString("vicinity");
            //phoneNum = restaurant.getString("phone");
        } catch(Exception e) {
            address = "error";
            Log.d("Error.Response", e.getLocalizedMessage());
        }
        final String finalAddress = address;
        //TODO: Replace hardcoded location with restaurant information
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("geo:0,0?q=5712+82nd+St+Unit+114, Lubbock, TX 79424"));
                intent.setData(Uri.parse("geo:"+finalAddress));
                SearchRestaurant.this.startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8067949898"));
                //intent.setData(Uri.parse("tel:"phoneNum));
                startActivity(intent);
            }
        });
    }

    /**
     * This method checks that the user has their location enabled and allows them to jump to the
     * settings to turn location on, if it is off.
     **/
    private void checkLocationSettings() {
        LocationManager lm = (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) && !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            new AlertDialog.Builder(getBaseContext())
                    .setMessage("Location services are not enabled")
                    .setPositiveButton("Open location settings", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            getBaseContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }
}