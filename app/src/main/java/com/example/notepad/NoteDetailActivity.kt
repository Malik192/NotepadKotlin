package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NoteDetailActivity : AppCompatActivity() {
//companion object instance d'une classe unique pour une classe singleton
   //companion de la class objet
    companion object Factory{
        val EXTRA_NOTE="note"
        val EXTRA_NOTE_index="noteIndex"

}
    lateinit var note: Note
    var note_index : Int=-1
    lateinit var title_view: TextView
    lateinit var text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        note =intent.getParcelableExtra(EXTRA_NOTE) as Note
        note_index = intent.getIntExtra(EXTRA_NOTE_index,-1)
        title_view=findViewById(R.id.title) as TextView
        text_view=findViewById(R.id.text) as TextView

        title_view.text=note.title
        text_view.text=note.text
    }
}
