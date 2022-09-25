package com.example.gymster

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PlannerFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    lateinit var chosenDate:ArrayList<String>
    lateinit var chosenExercises:ArrayList<String>
    lateinit var myDB:MyHelper
    lateinit var cursor: Cursor




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val view : View = inflater.inflate(R.layout.fragment_planner, container, false)
        val addWorkoutButton = view.findViewById<Button>(R.id.addWorkoutButton)
        addWorkoutButton.setOnClickListener {
            switchToAddWorkoutFragment()
        }
        myDB= MyHelper(requireContext())
        chosenDate = arrayListOf<String>()
        chosenExercises = arrayListOf<String>()




        //helperData = MyHelper(view.context)
        recyclerView=view.findViewById(R.id.fragmentPlanner_workoutRecycler)
        recyclerView.layoutManager= LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=TrainingAdapter(requireContext(),chosenDate,chosenExercises)
        displayData()





        return view;
    }

    private fun displayData() {
        cursor = myDB.readAllData()
        if(cursor.count==0){
            Toast.makeText(requireContext(),"No data!",Toast.LENGTH_SHORT).show()
        }else{
            while (cursor.moveToNext()){
                chosenDate.add(cursor.getString(1))
                chosenExercises.add(cursor.getString(2))
            }
        }
    }



    private fun switchToAddWorkoutFragment() {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer, AddWorkoutFragment())!!.addToBackStack("stozic")
        fragmentTransaction.commit()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlannerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}