package com.example.ontapandroid.database.reponsitory

import android.app.Application

import com.example.ontapandroid.database.dao.NoteDao
import com.example.ontapandroid.database.dao.NoteDataBase
import com.example.ontapandroid.model.Note
import kotlinx.coroutines.flow.Flow

class NoteReponsitory(app : Application) {
    private val noteDao : NoteDao
    init {
        val noteDataBase : NoteDataBase  = NoteDataBase.getInstance(app)
         noteDao = noteDataBase.noteDao()
    }

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    fun getNoteById(id: Int): Flow<Note> = noteDao.getNoteById(id)
}