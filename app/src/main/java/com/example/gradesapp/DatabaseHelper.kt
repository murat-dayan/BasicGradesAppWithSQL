package com.example.gradesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context, "grades.sqlite", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE grades" +
                " (note_id INTEGER PRIMARY KEY AUTOINCREMENT,lesson_name TEXT, note1 TEXT, note2 TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS grades")
        onCreate(db)
    }

}