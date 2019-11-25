package com.example.restaurantrandomizer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static final String CUISINE_SELECTED = "com.example.restaurantrandomizer.MESSAGE";
    Button cuisine;
    TextView cuisineSelected;
    String[] listCuisines;
    boolean[] checkCuisines;
    ArrayList<Integer> userCuisines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuisine = (Button) findViewById(R.id.btnCuisine);
        cuisineSelected = (TextView) findViewById(R.id.tvCuisineSelected);

        listCuisines = getResources().getStringArray(R.array.cuisines);
        checkCuisines = new boolean[listCuisines.length];

        cuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.alertDialog);
                builder.setTitle(getString(R.string.dialog_title));

                builder.setMultiChoiceItems(listCuisines, checkCuisines, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if(!userCuisines.contains(position)) {
                                userCuisines.add(position);
                            }
                        } else {
                            if(userCuisines.contains(position)) {
                                userCuisines.remove(userCuisines.indexOf(position));
                            }
                        }
                    }
                });

                builder.setCancelable(false);
                builder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cuisine = "";
                        ArrayList<Integer> sortedUserCuisines = userCuisines;
                        Collections.sort(sortedUserCuisines);
                        for (int i = 0; i < sortedUserCuisines.size(); i++){
                            cuisine = cuisine + listCuisines[sortedUserCuisines.get(i)];
                            if(i != sortedUserCuisines.size() - 1){
                                cuisine = cuisine + ", ";
                            }
                        }
                        cuisineSelected.setText(cuisine);
                    }
                });

                builder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkCuisines.length; i++){
                            checkCuisines[i] = false;
                            userCuisines.clear();
                            cuisineSelected.setText("");
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void indicatePrice(View view){
        Intent intent = new Intent(this, IndicatePriceInterval.class);
        TextView textView = (TextView) findViewById(R.id.tvCuisineSelected);
        String message = textView.getText().toString();
        intent.putExtra(CUISINE_SELECTED, message);
        startActivity(intent);
    }
}
