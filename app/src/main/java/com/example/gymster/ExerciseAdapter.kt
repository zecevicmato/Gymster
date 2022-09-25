package com.example.gymster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class ExerciseAdapter (private val exercisesList : ArrayList<Exercise>):
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exercisesList[(position)]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.exerciseName.text=currentItem.exerciseName
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val exerciseName :TextView = itemView.findViewById(R.id.workout)
    }
}
