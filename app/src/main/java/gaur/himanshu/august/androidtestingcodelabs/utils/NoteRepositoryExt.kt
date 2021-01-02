package gaur.himanshu.august.androidtestingcodelabs.utils

import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.repository.NoteRepository
import gaur.himanshu.august.androidtestingcodelabs.repository.RepositoryInterface
import kotlinx.coroutines.runBlocking

fun RepositoryInterface.insertNote(note: Note) = runBlocking {
    this@insertNote.insert(note)
}

fun RepositoryInterface.getAllNotesList()= runBlocking {
    this@getAllNotesList.getAllNotes()
}

fun RepositoryInterface.getAllNewss()= runBlocking {
    this@getAllNewss.getAllNews()
}