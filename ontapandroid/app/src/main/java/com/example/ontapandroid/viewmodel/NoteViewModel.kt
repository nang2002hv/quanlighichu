package com.example.ontapandroid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ontapandroid.database.reponsitory.NoteReponsitory
import com.example.ontapandroid.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application : Application) : ViewModel() {
    private val noteRepository = NoteReponsitory(application)
    val allNotes: Flow<List<Note>> = noteRepository.allNotes

    fun insert(note: Note) =  viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }



    fun getNoteById(id: Int): Flow<Note> = noteRepository.getNoteById(id)

    class NoteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}