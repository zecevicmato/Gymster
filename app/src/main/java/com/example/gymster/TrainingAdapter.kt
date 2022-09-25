package com.example.gymster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView


class TrainingAdapter(private val c: Context, dates:ArrayList<String>,exercises:ArrayList<String>):
    RecyclerView.Adapter<TrainingViewHolder>() {

    private val dates:ArrayList<String>
    private val exercises:ArrayList<String>
    init {
        this.dates=dates
        this.exercises=exercises
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.chosen_exercise_layout,parent,false)
        return TrainingViewHolder(v)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.date.setText(dates.get(position))
        holder.chosenExercises.setText(exercises.get(position))

    }

    override fun getItemCount(): Int {
        return dates.size
    }

}

