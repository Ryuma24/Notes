package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*
//Data access object(DAO)
@Dao
interface notesDao {
    //ignore data conflict with same id
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note:Note)



    @Delete
    fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC" )
    //livedata will observe the data and return the updated data
    fun getAllNotes(): LiveData<List<Note>>

        @Update
    fun update(note: Note)
}