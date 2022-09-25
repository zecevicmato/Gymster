package com.example.gymster

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*



class AddWorkoutFragment : Fragment() {

    private lateinit var builder: AlertDialog.Builder




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_add_workout, container, false)

////////////////calendar/////////////////////////////////////////////////////////////////////////////////////////
        val chosenDate = view.findViewById<TextView>(R.id.addWorkoutFragment_date)
        val chosenExercises=view.findViewById<TextView>(R.id.addWorkoutFragment_choosenExercises)

        val c = Calendar.getInstance();
        val year = c.get(Calendar.YEAR);
        val month = c.get(Calendar.MONTH);
        val day = c.get(Calendar.DAY_OF_MONTH);

        val addDateButton = view.findViewById<Button>(R.id.addWorkoutFragment_addDateButton);
        addDateButton.setOnClickListener {
            val dpd = DatePickerDialog(requireActivity(),R.style.DatePicker,DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay -> chosenDate.setText(""+mDay+"/"+mMonth+"/"+mYear)
            },year,month,day)
            
            dpd.show()
        }

        //biranje_vjezbi

        val legsButton = view.findViewById<AutoCompleteTextView>(R.id.exercisesLegs)
        legsButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())

            val legsExercisesArray = arrayOf("Barbell back squats",
                "Inverted Smith Machine Leg Press",
                "Single Leg Dumbbell Split Squat",
                "Dumbbell Lunge Step Ups",
                "Seated Machine Squats",
                "Leg Extensions",
                "Barbell Hip Thrust")


            val checkLegsExerciseArray = booleanArrayOf(false,false,false,false,false,false,false)
            val legExerciseList = Arrays.asList(*legsExercisesArray)
            builder.setTitle("SELECT EXERCISES FOR LEGS")
            builder.setMultiChoiceItems(legsExercisesArray,checkLegsExerciseArray){ dialog,which,isChecked-> checkLegsExerciseArray[which]=isChecked
                val currentItem = legExerciseList[which]
                //Toast.makeText(context,currentItem+" "+iscCHecked,Toast.LENGTH_SHORT).show()

            }
            builder.setPositiveButton("ADD"){ dialog,which ->
                for(i in checkLegsExerciseArray.indices){
                    val checked = checkLegsExerciseArray[i]
                    if(checked){
                        chosenExercises.text=chosenExercises.text.toString() + legExerciseList[i] + "\n"

                    }
                }


            }
            builder.setNeutralButton("Cancel"){ dialog,which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        val chestButton = view.findViewById<AutoCompleteTextView>(R.id.exerciseChest)
        chestButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())

            val chestExercisesArray = arrayOf("Incline Barbell Chest Press",
                "Bench Press",
                " Pec Deck",
                "Middle Cable Pec Fly")

            val checkChestExerciseArray = booleanArrayOf(false,false,false,false)

            val chestExerciseList = Arrays.asList(*chestExercisesArray)
            builder.setTitle("SELECT EXERCISES FOR CHEST")
            builder.setMultiChoiceItems(chestExercisesArray,checkChestExerciseArray){ dialog,which,iscCHecked->
                checkChestExerciseArray[which]=iscCHecked
                val currentItem = chestExerciseList[which]
                //Toast.makeText(context,currentItem+" "+iscCHecked,Toast.LENGTH_SHORT).show()

            }
            builder.setPositiveButton("ADD"){ dialog,which ->
                for(i in checkChestExerciseArray.indices){
                    val checked = checkChestExerciseArray[i]
                    if(checked){
                        chosenExercises.text=chosenExercises.text.toString() + chestExerciseList[i] + "\n"

                    }
                }
            }
            builder.setNeutralButton("Cancel"){ dialog,which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }

        val backButton = view.findViewById<AutoCompleteTextView>(R.id.exerciseBack)
        backButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())

            val backExercisesArray = arrayOf("Wide overhand grip lat pulldown",
                "Incline Bench Barbell Rows",
                "Bent Bar Seated Cable Row",
                "Standing Cable Rope Pushdown",
                "Hyperextensions")


            val checkBackExerciseArray = booleanArrayOf(false,false,false,false,false)

            val backExerciseList = Arrays.asList(*backExercisesArray)
            builder.setTitle("SELECT EXERCISES FOR BACK")
            builder.setMultiChoiceItems(backExercisesArray,checkBackExerciseArray){ dialog,which,iscCHecked->
                checkBackExerciseArray[which]=iscCHecked
                val currentItem = backExerciseList[which]
                //Toast.makeText(context,currentItem+" "+iscCHecked,Toast.LENGTH_SHORT).show()

            }
            builder.setPositiveButton("ADD"){ dialog,which ->
                for(i in checkBackExerciseArray.indices){
                    val checked = checkBackExerciseArray[i]
                    if(checked){
                        chosenExercises.text=chosenExercises.text.toString() + backExerciseList[i] + "\n"

                    }
                }
            }
            builder.setNeutralButton("Cancel"){ dialog,which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        val bicepsButton = view.findViewById<AutoCompleteTextView>(R.id.exerciseBiceps)
        bicepsButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())

            val bicepsExercisesArray = arrayOf("Barbell Biceps Curl",
                "Barbell Biceps Preacher Curl",
                "Standing Dumbbell Biceps Curls",
                "Standing Biceps Cable Curl")

            val checkBicepsExerciseArray = booleanArrayOf(false,false,false,false)

            val bicepsExerciseList = Arrays.asList(*bicepsExercisesArray)
            builder.setTitle("SELECT EXERCISES FOR BICEPS")
            builder.setMultiChoiceItems(bicepsExercisesArray,checkBicepsExerciseArray){ dialog,which,iscCHecked->
                checkBicepsExerciseArray[which]=iscCHecked
                val currentItem = bicepsExerciseList[which]
                //Toast.makeText(context,currentItem+" "+iscCHecked,Toast.LENGTH_SHORT).show()

            }
            builder.setPositiveButton("ADD"){ dialog,which ->
                for(i in checkBicepsExerciseArray.indices){
                    val checked = checkBicepsExerciseArray[i]
                    if(checked){
                        chosenExercises.text=chosenExercises.text.toString() + bicepsExerciseList[i] + "\n"

                    }
                }
            }
            builder.setNeutralButton("Cancel"){ dialog,which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        val tricepsButton = view.findViewById<AutoCompleteTextView>(R.id.exerciseTriceps)
        tricepsButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())

            val tricepsExercisesArray = arrayOf("Triceps Dips",
                "Triceps Dips Machine",
                "Cable V-Bar Triceps Pushdown",
                "Lying Barbell Triceps Extensions")

            val checkTricepsExerciseArray = booleanArrayOf(false,false,false,false)

            val tricepsExerciseList = Arrays.asList(*tricepsExercisesArray)
            builder.setTitle("SELECT EXERCISES FOR TRICEPS")
            builder.setMultiChoiceItems(tricepsExercisesArray,checkTricepsExerciseArray){ dialog,which,iscCHecked->
                checkTricepsExerciseArray[which]=iscCHecked
                val currentItem = tricepsExerciseList[which]
                //Toast.makeText(context,currentItem+" "+iscCHecked,Toast.LENGTH_SHORT).show()

            }
            builder.setPositiveButton("ADD"){ dialog,which ->
                for(i in checkTricepsExerciseArray.indices){
                    val checked = checkTricepsExerciseArray[i]
                    if(checked){
                        chosenExercises.text=chosenExercises.text.toString() + tricepsExerciseList[i] + "\n"

                    }
                }
            }
            builder.setNeutralButton("Cancel"){ dialog,which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()

        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////OVDJE_KRECE_SQL//////////////////////////////////////////////////////

        var helper = MyHelper(requireContext())
        var db:SQLiteDatabase = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM TRAINING",null)



        val addTraining = view.findViewById<FloatingActionButton>(R.id.floatingButton)
        addTraining.setOnClickListener {
            helper.addTraining(chosenDate.text.toString(),chosenExercises.text.toString())
            rs.requery()
            switchActivity()

        }
        /*addTraining.setOnClickListener {
            var cv = ContentValues()
            cv.put("DATE",chosenDate.text.toString())
            cv.put("EXERCISES",chosenExercises.text.toString())

            db.insert("TRAINING",null,cv)
            rs.requery()
            switchActivity()
        }*/







        return view;
    }


    private fun switchActivity() {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer, PlannerFragment())!!.addToBackStack("stozic")
        fragmentTransaction.commit()
    }


}


