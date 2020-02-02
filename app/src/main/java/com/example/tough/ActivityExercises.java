package com.example.tough;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;




public class ActivityExercises extends Activity  implements  ExerciseClickListener {

    private static final String PREFS_TAG = "com.example.tough.EXERCISES";
    private static final String KEY_PREFS = "com.example.tough.exercise";
    private static final String EXTRA_KEY ="com.example.application.tough.EXTRA_TEXT" ;
    private ArrayList<Exercise> exercises;
    private RecyclerView recycler;
    private RecyclerAdapter adapter;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exercises_activity);
        setupBackButton();
        setupRecycler();
        setupSpinner();

    }


    public void setupRecycler(){

        exercises = new ArrayList<>();
        ArrayList<Exercise> sharedExercises = new ArrayList<>();

        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter( ActivityExercises.this, this);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
        String serializedObject = sharedPref.getString(KEY_PREFS, null);
        if(serializedObject != null){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Exercise>>(){}.getType();
            sharedExercises = gson.fromJson(serializedObject, type);
        }

        addExerciseByType(sharedExercises);

        adapter.addData(exercises);
        recycler.setAdapter(adapter);

    }

    public void addExerciseByType(ArrayList<Exercise> sharedExercises){

        for (int i = 0; i < sharedExercises.size(); i++){
            if (sharedExercises.get(i).getType().equals(getExtras())){
                exercises.add(sharedExercises.get(i));
            }
        }
    }


    public String getExtras(){

        Intent workouts = getIntent();
        return  workouts.getStringExtra(EXTRA_KEY);

    }



    public void setupSpinner() {

        Spinner spinner = findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapterExercises = ArrayAdapter.createFromResource(this, R.array.equipment, android.R.layout.simple_spinner_item);
        adapterExercises.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterExercises);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = parent.getItemAtPosition(position).toString();
                if(text.equals("Any equipment")){

                    adapter.addData(exercises);
                    recycler.setAdapter(adapter);

                }
                else {
                    searchPattern(text);
                }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void searchPattern(String text){
        ArrayList<Exercise> list = new ArrayList<>();
        for (Exercise object : exercises){
            if(object.getEquipment().toLowerCase().contains(text.toLowerCase())){
                list.add(object);
            }

        }

        adapter.addData(list);
        recycler.setAdapter( adapter);
    }


    public void setupBackButton(){

        Button bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onExerciseClick(int position) {

        adapter.openDialog(position, this);

    }
}
