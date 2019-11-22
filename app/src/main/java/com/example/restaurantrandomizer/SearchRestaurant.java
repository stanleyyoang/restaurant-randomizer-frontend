package com.example.restaurantrandomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.net.Uri;

public class SearchRestaurant extends AppCompatActivity {

    private ProgressBar nProgressBar;
    Button map;
    Button phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        nProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        map = (Button) findViewById(R.id.button5);
        phone = (Button) findViewById(R.id.button6);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:37.7749,-122.4194?q=restaurants"));
                SearchRestaurant.this.startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });

    }
}
