package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val repository: NotesRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNodeDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNode (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNode (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}