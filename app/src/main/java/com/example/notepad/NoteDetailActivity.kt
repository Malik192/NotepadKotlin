package com.example.notepad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView

class NoteDetailActivity : AppCompatActivity() {
//companion object instance d'une classe unique pour une classe singleton
   //companion de la class objet
    companion object {
        val EXTRA_NOTE="note"
        val EXTRA_NOTE_index="noteIndex"
        val REQUEST_EDIT=1


}
    lateinit var note: Note
    //result ok
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     // ajout de activity note detail du menu a activity note detail de layout
        menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_save ->{
                saveNote()
                return true
            }
        }

            return super.onOptionsItemSelected(item)
    }

    fun saveNote(){

        note.title= title_view.text.toString();
        note.text=text_view.text.toString()
        intent= Intent()
        intent.putExtra(EXTRA_NOTE,note as Parcelable)
        intent.putExtra(EXTRA_NOTE_index,note_index)

        setResult(Activity.RESULT_OK,intent)
        finish()
        //finish pour revenir a l'activity precedente
    }
}
