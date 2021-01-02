package gaur.himanshu.august.androidtestingcodelabs.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gaur.himanshu.august.androidtestingcodelabs.Result
import gaur.himanshu.august.androidtestingcodelabs.Status
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article

class FakeNoteRepositoryAndroidTest() : RepositoryInterface {


    private val _databaseList = mutableListOf<Note>()
    private val mutableDatabaseList = MutableLiveData<MutableList<Note>>()
    private val databaseList: LiveData<MutableList<Note>> = mutableDatabaseList



    fun getList()=_databaseList

    private val networkError = false


    private fun refreshData(){
        mutableDatabaseList.postValue(_databaseList)

    }

    override suspend fun insert(note: Note) {

        _databaseList.add(note)
        refreshData()
    }

    override suspend fun delete(note: Note) {
        _databaseList.remove(note)
        refreshData()
    }

    override suspend fun getAllNotes(): List<Note> {
        return _databaseList.toList()
    }

    override suspend fun getAllNews(): Result<List<Article>> {
        return if (networkError) {
            Result(Status.ERROR, null, null)
        } else {
            Result(Status.SUCCESS, listOf(), null)
        }
    }
}