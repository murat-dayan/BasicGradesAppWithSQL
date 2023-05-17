package com.example.gradesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gradesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var notesList: ArrayList<Notes>
    private lateinit var mainrvnotesadapter: NotesAdapter
    private lateinit var dbh:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.toolbarMain.title= "Notes App"

        setSupportActionBar(binding.toolbarMain)

        binding.rvMain.setHasFixedSize(true)
        binding.rvMain.layoutManager= LinearLayoutManager(this)

        dbh= DatabaseHelper(this)
        notesList= Notesdao().allNotes(dbh)

        mainrvnotesadapter= NotesAdapter(this, notesList)
        binding.rvMain.adapter= mainrvnotesadapter

        var sumNotes=0
        for (n in notesList){
            sumNotes= sumNotes + (n.note1+n.note2)/2
        }
        if(sumNotes != 0){
            binding.toolbarMain.subtitle= "Grades Average: ${sumNotes/notesList.size}"
        }

        binding.fabButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NotRecordActivity::class.java))
        }

    }

    override fun onBackPressed() {

        val intent= Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

        super.onBackPressed()
    }

}