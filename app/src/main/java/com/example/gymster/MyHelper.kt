package com.example.gymster

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception
import java.security.AccessControlContext
import java.util.*
import kotlin.collections.ArrayList

class MyHelper(context : Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {


    lateinit var cursor:Cursor
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "USERS"
        const val TRAINING = "TRAINING"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERSTable(_id integer primary key autoincrement,FULLNAME TEXT,HEIGHT INTEGER,WEIGHT DOUBLE)")
        db?.execSQL("CREATE TABLE TRAINING(_key integer primary key autoincrement,DATE TEXT,EXERCISES TEXT)")
        db?.execSQL("INSERT INTO USERSTable(FULLNAME,HEIGHT,WEIGHT) VALUES('Mato Zecevic','175','70')")

        //db?.execSQL("CREATE TABLE TRAINING(_key integer primary key autoincrement,DATE TEXT,EXERCISES TEXT)")
        //db?.execSQL("INSERT INTO TRAINING(DATE,EXERCISES) VALUES('21/8/2022','NOGOMET,BADMINTON')" )

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TRAINING);
        onCreate(db);
    }

    fun addTraining(chosenDate:String,chosenExercises: String){
        var db:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put("DATE",chosenDate)
        cv.put("EXERCISES",chosenExercises)
        db.insert(TRAINING,null,cv)
    }

    fun readAllData(): Cursor {
        var db:SQLiteDatabase = this.readableDatabase
        cursor=db.rawQuery("SELECT * FROM TRAINING",null)
        return cursor
    }






}


