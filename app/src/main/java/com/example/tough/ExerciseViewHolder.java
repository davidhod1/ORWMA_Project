package com.example.tough;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




public class ExerciseViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    private TextView tvWorkoutName;
    private ImageView ivWorkout;
    private ExerciseClickListener clickListener;


    public ExerciseViewHolder(@NonNull View itemView, ExerciseClickListener listener) {
        super(itemView);

        this.clickListener = listener;
        tvWorkoutName = itemView.findViewById(R.id.tvNameWorkout);
        ivWorkout = itemView.findViewById(R.id.ivWorkout);
        itemView.setOnClickListener(this);

    }

    public ImageView getIvWorkout() {
        return ivWorkout;
    }

    public void setText(String name) {tvWorkoutName.setText(name);}

    @Override
    public void onClick(View v) {
        clickListener.onExerciseClick(getAdapterPosition());
    }
}
