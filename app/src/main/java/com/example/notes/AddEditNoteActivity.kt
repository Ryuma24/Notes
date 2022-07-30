package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleEdit:EditText
    lateinit var noteDescription:EditText
    lateinit var addUpdateBttn:Button
    lateinit var viewModel:NoteViewModel
    var noteId=-1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        noteTitleEdit=findViewById(R.id.EditNoteTitle)
        noteDescription=findViewById(R.id.EditNoteDescription)
        addUpdateBttn=findViewById(R.id.BttnAddUpdate)
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        val noteType=intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDesc=intent.getStringExtra("noteDescription")
            noteId=intent.getIntExtra("noteID",-1)
            addUpdateBttn.setText("Update Note")
            noteTitleEdit.setText(noteTitle)
            noteDescription.setText(noteDesc)

        }else{
            addUpdateBttn.setText("Save Note")
        }
        addUpdateBttn.setOnClickListener{
            val noteTitle=noteTitleEdit.text.toString()
            val noteDescription=noteDescription.text.toString()
            if(noteType.equals("Edit")){
                if(noteTitle.isNotEmpty()&&noteDescription.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM,yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=Note(noteTitle,noteDescription,currentDate)
                    updateNote.id=noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated...",Toast.LENGTH_LONG).show()

                }
            }else{
                if(noteTitle.isNotEmpty()&&noteDescription.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM,yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText(this,"Note Added",Toast.LENGTH_LONG).show()

                }

            }
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }

    }
}