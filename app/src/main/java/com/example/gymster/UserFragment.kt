package com.example.gymster

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.roundToInt
import kotlin.text.contains as contains


class UserFragment : Fragment() {

    lateinit var cursor:Cursor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_user, container, false)
        Log.d("tag","This is checking message")
        var helper = MyHelper(requireContext())
        var db = helper.readableDatabase

        val fullName = view.findViewById<TextView>(R.id.userFragment_user)
        val height = view.findViewById<TextView>(R.id.userFragment_height)
        val weight = view.findViewById<EditText>(R.id.userFragment_weight)


        var rs = db.rawQuery("SELECT * FROM USERSTable",null)
        if(rs.moveToLast()){
            fullName.setText(rs.getString(1))
            weight.setText(rs.getString(3))
            height.setText(rs.getString(2))
        }

        val changeButton = view.findViewById<Button>(R.id.changeButton)
        changeButton.setOnClickListener {
            var cv = ContentValues()
            cv.put("WEIGHT",weight.text.toString())
            db.update("USERSTable",cv,"_id=?", arrayOf(rs.getString(0)))
            rs.requery()
        }

        val chestKeywords = arrayOf("Incline Barbell Chest Press", "Bench Press", "Pec Deck",
            "Middle Cable Pec Fly")

        val legsKeywords = arrayOf("Barbell back squats", "Inverted Smith Machine Leg Press", "Single Leg Dumbbell Split Squat",
            "Dumbbell Lunge Step Ups", "Seated Machine Squats","Leg Extensions", "Barbell Hip Thrust")

        val bicepsKeywords= arrayOf("Barbell Biceps Curl",
            "Barbell Biceps Preacher Curl",
            "Standing Dumbbell Biceps Curls",
            "Standing Biceps Cable Curl")

        val tricepsKeywords= arrayOf("Triceps Dips",
            "Triceps Dips Machine",
            "Cable V-Bar Triceps Pushdown",
            "Lying Barbell Triceps Extensions")

        val backKeyWords=arrayOf("Wide overhand grip lat pulldown",
            "Incline Bench Barbell Rows",
            "Bent Bar Seated Cable Row",
            "Standing Cable Rope Pushdown")

        var chestCounter:Double = 0.0
        var legsCounter:Double = 0.0
        var bicepsCounter:Double = 0.0
        var tricepsCounter: Double = 0.0
        var backCounter:Double = 0.0
        var workout:String


        cursor= db.rawQuery("SELECT * FROM TRAINING",null)

        if(cursor.moveToFirst()){
            do {
                workout= cursor.getString(2)
                for (i in 0 until tricepsKeywords.size) {
                    if (workout.contains(tricepsKeywords.get(i))) tricepsCounter++
                }
                for (i in tricepsKeywords.indices) {
                    if (workout.contains(bicepsKeywords.get(i))) bicepsCounter++
                }
                for (i in tricepsKeywords.indices) {
                    if (workout.contains(legsKeywords.get(i))) legsCounter++
                }
                for (i in tricepsKeywords.indices) {
                    if (workout.contains(backKeyWords.get(i))) backCounter++
                }
                for (i in tricepsKeywords.indices) {
                    if (workout.contains(chestKeywords.get(i))) chestCounter++
                }
            } while (cursor.moveToNext())}

        val suma = doubleArrayOf(tricepsCounter,bicepsCounter,legsCounter,backCounter,chestCounter)
        val sum = suma.sum()

        val tricepsStats=view.findViewById<TextView>(R.id.tricepsStats)
        val bicepsStats=view.findViewById<TextView>(R.id.bicepsStats)
        val backStats=view.findViewById<TextView>(R.id.backStats)
        val chestStats=view.findViewById<TextView>(R.id.chestStats)
        val legsStats=view.findViewById<TextView>(R.id.legsStats)


        var triceps : Double

        if(sum!=0.0){
            triceps = ((tricepsCounter/sum)*100).roundToInt().toDouble()
            tricepsStats.text= triceps.toString()+"%"

            val biceps =((bicepsCounter/sum)*100).roundToInt().toDouble()
            bicepsStats.text=biceps.toString()+"%"

            val back = ((backCounter/sum)*100).roundToInt().toDouble()
            backStats.text=back.toString()+"%"

            val legs = ((legsCounter/sum)*100).roundToInt().toDouble()
            legsStats.text=legs.toString()+"%"

            val chest = ((chestCounter/sum)*100).roundToInt().toDouble()
            chestStats.text=chest.toString()+"%"

        } else {
            tricepsStats.text= "0%"
            bicepsStats.text="0%"
            chestStats.text="0%"
            legsStats.text="0%"
            backStats.text="0%"
        }

        cursor.close()
        db.close()

        return view
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            UserFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}