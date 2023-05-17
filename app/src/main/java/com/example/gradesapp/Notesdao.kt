package com.example.gradesapp

import android.content.ContentValues

class Notesdao {

    fun allNotes(dbh: DatabaseHelper) : ArrayList<Notes>{
        val db= dbh.writableDatabase
        val notesList= ArrayList<Notes>()

        val c= db.rawQuery("SELECT*FROM grades", null)

        while (c.moveToNext()){
            val note= Notes(c.getInt(c.getColumnIndexOrThrow("note_id")),
                            c.getString(c.getColumnIndexOrThrow("lesson_name")),
                            c.getInt(c.getColumnIndexOrThrow("note1")),
                            c.getInt(c.getColumnIndexOrThrow("note2")))

            notesList.add(note)
        }
        return notesList
    }

    fun deleteNote(dbh:DatabaseHelper, note_id:Int){
        val db= dbh.writableDatabase
        db.delete("grades","note_id=?", arrayOf(note_id.toString()))
        db.close()
    }

    fun addNote(dbh: DatabaseHelper, lesson_name:String,note1:Int,note2:Int){

        val db= dbh.writableDatabase

        val values= ContentValues()
        values.put("lesson_name",lesson_name)
        values.put("note1",note1)
        values.put("note2", note2)

        db.insertOrThrow("grades",null,values)
        db.close()

    }

    fun updateNote(dbh: DatabaseHelper,note_id:Int, lesson_name:String,note1:Int,note2:Int){

        val db= dbh.writableDatabase

        val values= ContentValues()
        values.put("lesson_name",lesson_name)
        values.put("note1",note1)
        values.put("note2", note2)

        db.update("grades",values,"note_id=?", arrayOf(note_id.toString()))
        db.close()

    }

}