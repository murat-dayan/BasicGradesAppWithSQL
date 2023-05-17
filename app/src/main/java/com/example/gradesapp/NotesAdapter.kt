package com.example.gradesapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val mContext:Context, private val notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.cardViewHolder>()
{

    inner class cardViewHolder(view: View): RecyclerView.ViewHolder(view){

        var cardViewNotes: CardView
        var cardViewLessonText: TextView
        var cardViewNote1: TextView
        var cardViewNote2: TextView

        init {
            cardViewNotes= view.findViewById(R.id.cardViewNotes)
            cardViewLessonText= view.findViewById(R.id.cardViewLessonText)
            cardViewNote1= view.findViewById(R.id.cardViewNote1)
            cardViewNote2= view.findViewById(R.id.cardViewNote2)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewHolder {
        val design= LayoutInflater.from(mContext).inflate(R.layout.cardview_notes,parent,false)
        return cardViewHolder(design)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: cardViewHolder, position: Int) {
        val note= notesList.get(position)

        holder.cardViewLessonText.text= note.lesson_name
        holder.cardViewNote1.text= (note.note1).toString()
        holder.cardViewNote2.text= (note.note2).toString()

        holder.cardViewNotes.setOnClickListener {
            val intent= Intent(mContext, DetailActivity::class.java)
            intent.putExtra("object", note)
            mContext.startActivity(intent)
        }
    }

}