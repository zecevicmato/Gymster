package com.example.gymster

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrainingViewHolder(v: View):RecyclerView.ViewHolder(v) {
    val date = v.findViewById<TextView>(R.id.workoutDate)
    val chosenExercises = v.findViewById<TextView>(R.id.chosenExercises)
}