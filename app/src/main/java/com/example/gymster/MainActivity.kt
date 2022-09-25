package com.example.gymster

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.core.content.contentValuesOf

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullName = findViewById<EditText>(R.id.mainActivity_fullName)
        val height = findViewById<EditText>(R.id.mainActivity_Height)
        val weight = findViewById<EditText>(R.id.mainActivity_Weight)
        val registered = findViewById<Button>(R.id.alreadyHaveAcc)

        var helper = MyHelper(applicationContext)
        var db:SQLiteDatabase = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM USERSTable",null)


        registered.setOnClickListener { SwitchActivity() }

        val saveButton = findViewById<Button>(R.id.mainActivity_saveButton)
        saveButton.setOnClickListener {
            var cv = ContentValues()
            cv.put("FULLNAME",fullName.text.toString())
            cv.put("HEIGHT",height.text.toString())
            cv.put("WEIGHT",weight.text.toString())
            db.insert("USERSTable",null,cv)
            rs.requery()
            SwitchActivity()
        }
    }

    private fun SwitchActivity() {
        val intent = Intent(this,BeginnerActivity::class.java)
        startActivity(intent)
    }
}