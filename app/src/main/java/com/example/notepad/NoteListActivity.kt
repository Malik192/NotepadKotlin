package com.example.notepad

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
        notes.add(Note("Note 1","BLACK"))
        notes.add(Note("Note 2","BLACK"))
        notes.add(Note("Note 3","cscdcs"))
        notes.add(Note("Note 4","cdosdcdscdscdj"))
        notes.add(Note("Note 5","cdoscsccsdcs"))
        notes.add(Note("Note 6","cdocsodj"))
//initialisation de l'adapteur en implementant view onclick
        adapter= NoteAdapter(notes,this)

        //RecyclerView
        val recyclerview =findViewById(R.id.notes_recycler_view) as RecyclerView
        //recuperer le recycler view en initialisant le layout manager
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
    //requestCode =1
    //result


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data==null ){
            return
        }
        when (requestCode)
        {
            NoteDetailActivity.REQUEST_EDIT->processEditNote(data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processEditNote(data: Intent) {
        val note : Note=data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
        val noteIndex=data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_index,-1)
        saveNote(note,noteIndex)
    }

    private fun saveNote(note: Note, noteIndex: Int) {
        notes[noteIndex]= note
        adapter.notifyDataSetChanged()
    }

    fun showNoteDetail(Index :Int){
        val note=notes[Index]
        val intent=Intent(this,NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE,note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_index,Index)
        startActivityForResult(intent,NoteDetailActivity.REQUEST_EDIT)
    }

}