package com.example.notepad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//definition  des notes


class NoteListActivity :AppCompatActivity(), View.OnClickListener {

    //ajout ou suppression d'element (liste de notes)
    lateinit var notes : MutableList<Note>

    //adapter
    lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        notes= mutableListOf<Note>()
        notes.add(Note("Note 1","BLACKY"))
        notes.add(Note("malik","BLACKY"))
        notes.add(Note("Nabil","cscdcs"))
        notes.add(Note("linda","cdosdcdscdscdj"))
        notes.add(Note("maman","cdoscsccsdcs"))
        notes.add(Note("papa","cdocsodj"))
//initialisation de l'adapteur en implementant view onclick
        adapter= NoteAdapter(notes,this)

        //RecycleView
        val recyclerview =findViewById(R.id.notes_recycler_view) as RecyclerView
        //recuperer le recycler view en initialisant le layout lanager
        recyclerview.layoutManager = LinearLayoutManager(this)
 //connecter le recycler view a l'adapter
        recyclerview.adapter=adapter

    }

    override fun onClick(view: View) {
        if (view.tag !=null){
            //recuperer l'evenement de click
            showNoteDetail(view.tag as Int)
        }

    }
    fun showNoteDetail(Index :Int){
        val note=notes[Index]
        val intent=Intent(this,NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.Factory.EXTRA_NOTE,note)
        intent.putExtra(NoteDetailActivity.Factory.EXTRA_NOTE_index,Index)
    startActivity(intent)
    }

}