package com.example.gradesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.gradesapp.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var note:Notes
    private lateinit var dbh:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_detail)

        dbh= DatabaseHelper(this)

        binding.toolbarNoteDetail.title= "Detail"
        setSupportActionBar(binding.toolbarNoteDetail)

        note= intent.getSerializableExtra("object") as Notes

        binding.editTextLesson.setText(note.lesson_name)
        binding.editTextNote1.setText((note.note1).toString())
        binding.editTextNote2.setText((note.note2).toString())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_delete ->{
                Snackbar.make(binding.toolbarNoteDetail, "Delete this?", Snackbar.LENGTH_SHORT)
                    .setAction("YES"){

                        Notesdao().deleteNote(dbh,note.note_id)

                        startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                        finish()
                    }.show()
                return true
            }
            R.id.action_edit -> {

                val lessonName= binding.editTextLesson.text.toString().trim()
                val note1= binding.editTextNote1.text.toString().trim()
                val note2= binding.editTextNote2.text.toString().trim()

                if (TextUtils.isEmpty(lessonName)){
                    Snackbar.make(binding.toolbarNoteDetail, "Please enter the lesson name", Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(note1)){
                    Snackbar.make(binding.toolbarNoteDetail, "Please enter the note 1", Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(note2)){
                    Snackbar.make(binding.toolbarNoteDetail, "Please enter the note 2", Snackbar.LENGTH_SHORT).show()
                    return false
                }

                Notesdao().updateNote(dbh,note.note_id,lessonName,note1.toInt(),note2.toInt())


                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
                finish()
                return true
            }
            else -> return false
        }
    }

}