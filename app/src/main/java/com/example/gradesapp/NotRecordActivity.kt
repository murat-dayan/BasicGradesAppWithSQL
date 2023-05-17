package com.example.gradesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import com.example.gradesapp.databinding.ActivityNotRecordBinding
import com.google.android.material.snackbar.Snackbar

class NotRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotRecordBinding
    private lateinit var dbh:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_not_record)

        dbh= DatabaseHelper(this)



        binding.toolbarNotRecord.title= "Notes Record"
        setSupportActionBar(binding.toolbarNotRecord)

        binding.buttonRecord.setOnClickListener {

            val lessonName= binding.editTextLesson.text.toString().trim()
            val note1= binding.editTextNote1.text.toString().trim()
            val note2= binding.editTextNote2.text.toString().trim()

            if (TextUtils.isEmpty(lessonName)){
                Snackbar.make(binding.toolbarNotRecord, "Please enter the lesson name", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(note1)){
                Snackbar.make(binding.toolbarNotRecord, "Please enter the note 1", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(note2)){
                Snackbar.make(binding.toolbarNotRecord, "Please enter the note 2", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Notesdao().addNote(dbh,lessonName,note1.toInt(),note2.toInt())

            startActivity(Intent(this@NotRecordActivity, MainActivity::class.java))
            finish()
        }

    }
}