package com.example.tough;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;


import java.util.ArrayList;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends Activity {

    private static final String MY_PREFS_NAME = "com.example.tough.EXERCISES" ;
    DatabaseReference reference;
    private ArrayList<Exercise> exercises;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupSplashScreen();
        setupDatabaseData();

    }


    public void setupSplashScreen(){

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen().withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2000)
                .withBackgroundResource(R.drawable.arnold)
                .withFooterText("Failure is not an option Everyone has to succeed.");

        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextSize(30);
        config.getFooterTextView().setGravity(Gravity.CENTER);
        config.getFooterTextView().setTypeface(null, Typeface.BOLD_ITALIC);
        config.getFooterTextView().setPadding(0, 0 ,0 , 20);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);

    }

    public void setupDatabaseData(){

        exercises = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Exercises");
        Log.println(Log.ASSERT, "TAG", reference.toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                exercises.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Exercise exercise = postSnapshot.getValue(Exercise.class);
                    exercises.add(exercise);

                }

                sharedPreferences = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(exercises);

                editor.putString("com.example.tough.exercise", json);
                editor.apply();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SplashScreen.this,databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}