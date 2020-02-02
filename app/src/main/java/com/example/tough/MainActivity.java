package com.example.tough;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private static final String EXTRA_KEY = "com.example.application.tough.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeButtons();


    }

    public void initializeButtons(){

       Button bChest = (Button) findViewById(R.id.buttonChest);
       Button bAbs = (Button) findViewById(R.id.buttonAbs);
       Button bBack = (Button) findViewById(R.id.buttonBack);
       Button bLegs = (Button) findViewById(R.id.buttonLegs);

        bChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("Chest");
            }
        });
        bAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("Abs");
            }
        });
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("Back");
            }
        });
        bLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeIntent("Legs");
            }
        });





    }

    private void initializeIntent(String workout){

        Intent workouts = new Intent(MainActivity.this, ActivityExercises.class);
        workouts.putExtra(EXTRA_KEY, workout);
        startActivity(workouts);

    }
}
