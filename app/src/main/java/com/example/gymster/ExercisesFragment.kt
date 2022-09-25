package com.example.gymster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ExercisesFragment : Fragment() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Exercise>
    lateinit var imageId:Array<Int>
    lateinit var exerciseTitle:Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_exercises, container, false)

        imageId= arrayOf(
            //noge
            R.drawable.bbl_back_squats,
            R.drawable.inverted_smith_machine_leg_press,
            R.drawable.single_leg_dumbell_squat,
            R.drawable.dumbell_lunge_step_up,
            R.drawable.seated_machine_squats,
            R.drawable.leg_extensions,
            R.drawable.barbell_hip_thrust,
            //triceps
            R.drawable.triceps_dips,
            R.drawable.triceps_dips_machine,
            R.drawable.cable_v_bar_pushdown,
            R.drawable.lyng_bbl_triceps_ext,
            //leđa
            R.drawable.wide_overhand_grip_lat,
            R.drawable.incline_bench_bbl_rows,
            R.drawable.bent_bar_cable_row,
            R.drawable.stand_cable_rope_push,
            R.drawable.hyperextensions,
            //prsa
            R.drawable.incline_bbl_chest_press,
            R.drawable.bench_press,
            R.drawable.pec_deck,
            R.drawable.middle_cable_fly,
            //biceps
            R.drawable.bbl_biceps_curl,
            R.drawable.bbl_biceps_preacher_curl,
            R.drawable.standing_dumbell_biceps_curl,
            R.drawable.standing_cable_curl

        )
        exerciseTitle= arrayOf(
            //noge
            "Barbell back squats",
            "Inverted Smith Machine Leg Press",
            "Single Leg Dumbbell Split Squat",
            "Dumbbell Lunge Step Ups",
            "Seated Machine Squats",
            "Leg Extensions",
            "Barbell Hip Thrust",
            //triceps
            "Triceps Dips",
            "Triceps Dips Machine",
            "Cable V-Bar Triceps Pushdown",
            "Lying Barbell Triceps Extensions",
            //leđa
            "Wide overhand grip lat pulldown",
            "Incline Bench Barbell Rows",
            "Bent Bar Seated Cable Row",
            "Standing Cable Rope Pushdown",
            "Hyperextensions",
            //prsa
            "Incline Barbell Chest Press",
            "Bench Press",
            " Pec Deck",
            "Middle Cable Pec Fly",
            //biceps
            "Barbell Biceps Curl",
            "Barbell Biceps Preacher Curl",
            "Standing Dumbbell Biceps Curls",
            "Standing Biceps Cable Curl"


        )

        newRecyclerView=view.findViewById(R.id.exercisesFragment_recyclerView)
        newRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Exercise>()
        getExerciseData()


        return view;
    }

    private fun getExerciseData() {
        for(i in imageId.indices){
            val exercise = Exercise(imageId[i],exerciseTitle[i])
            newArrayList.add(exercise)
        }
        newRecyclerView.adapter=ExerciseAdapter(newArrayList)

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ExercisesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}