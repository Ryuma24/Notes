package com.example.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//export schema is used by room to export database's schema info to json file at compile time
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false )
abstract class NotesDatabase : RoomDatabase(){
    abstract fun getNotesData():notesDao

    companion object{

        @Volatile

        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}