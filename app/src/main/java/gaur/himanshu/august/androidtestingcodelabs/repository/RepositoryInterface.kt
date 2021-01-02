package gaur.himanshu.august.androidtestingcodelabs.repository

import gaur.himanshu.august.androidtestingcodelabs.Result
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article

interface RepositoryInterface {

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)

    suspend fun getAllNotes(): List<Note>

    suspend fun getAllNews(): Result<List<Article>>


}