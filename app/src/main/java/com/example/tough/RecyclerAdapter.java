package com.example.tough;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<ExerciseViewHolder>    {

    private List<Exercise> exercises = new ArrayList<>();
    private ExerciseClickListener clickListener;
    private Context context;

    public RecyclerAdapter(ExerciseClickListener clickListener, Context context) {

        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_cell, parent, false);
        return new ExerciseViewHolder(myView, clickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {

        holder.setText(exercises.get(position).getName());
        Glide.with(context).asGif().load(exercises.get(position).getImage()).into(holder.getIvWorkout());

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void addData(List<Exercise> data){

        exercises.clear();
        exercises.addAll(data);
        notifyDataSetChanged();

    }


    public void openDialog(int position, Context context){



        final Dialog exerciseDialog = new Dialog(context);
        exerciseDialog.setContentView(R.layout.dialog_exercise);
//        exerciseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView ivExercise = (ImageView) exerciseDialog.findViewById(R.id.ivExercise);
        TextView tvExerciseName = (TextView) exerciseDialog.findViewById(R.id.tvDialog_name);
        TextView tvExerciseDescription = (TextView) exerciseDialog.findViewById((R.id.tvDialog_description));
        Button bCloseDialog = (Button) exerciseDialog.findViewById(R.id.bCloseDialog);
        tvExerciseDescription.setMovementMethod(new ScrollingMovementMethod());

        Glide.with(context).asGif().load(exercises.get(position).getImage()).into(ivExercise);
        tvExerciseName.setText(exercises.get(position).getName());
        tvExerciseDescription.setText(exercises.get(position).getDescription());

        exerciseDialog.show();

        bCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseDialog.cancel();
            }
        });
    }



}
