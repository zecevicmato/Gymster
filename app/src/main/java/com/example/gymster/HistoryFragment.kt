package com.example.gymster

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HistoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    //private lateinit var newArrayList: ArrayList<Training>
    lateinit var chosenDate:ArrayList<String>
    lateinit var chosenExercises:ArrayList<String>
    lateinit var myDB:MyHelper
    lateinit var cursor: Cursor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_history, container, false)

        myDB= MyHelper(requireContext())
        chosenDate = arrayListOf<String>()
        chosenExercises = arrayListOf<String>()



        recyclerView=view.findViewById(R.id.historyFragment_recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)



        recyclerView.adapter = TrainingAdapter(requireContext(),chosenDate,chosenExercises)
        displayData()


        return view;
    }

    private fun displayData() {
        cursor = myDB.readAllData()
        if(cursor.count==0){
            Toast.makeText(requireContext(),"No data!",Toast.LENGTH_SHORT).show()
        }else{
            while (cursor.moveToNext()){
                chosenExercises.add(cursor.getString(2))
                chosenDate.add(cursor.getString(1))

            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            HistoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}


