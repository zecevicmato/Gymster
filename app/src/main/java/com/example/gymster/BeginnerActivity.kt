package com.example.gymster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class BeginnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beginner)
        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottomNavigation)
        
        
        addFragment(PlannerFragment.newInstance())
        bottomNavigation.show(0)
        bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_planner))
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_history))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_exercises))
        bottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_user))

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(PlannerFragment.newInstance())
                }
                1 -> {
                    replaceFragment(HistoryFragment.newInstance())
                }
                2 -> {
                    replaceFragment(ExercisesFragment.newInstance())
                }
                3 -> {
                    replaceFragment(UserFragment.newInstance())
                }
                else -> {

                    replaceFragment(PlannerFragment.newInstance())
                }
            }
        }
    }

    private fun addFragment(fragment:Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()

    }
}