package gaur.himanshu.august.androidtestingcodelabs.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gaur.himanshu.august.androidtestingcodelabs.Events
import gaur.himanshu.august.androidtestingcodelabs.Result
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import gaur.himanshu.august.androidtestingcodelabs.repository.RepositoryInterface
import gaur.himanshu.august.androidtestingcodelabs.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteViewModels @ViewModelInject constructor(
    private val noteRepository: RepositoryInterface
) : ViewModel() {


    private val _noteList = MutableLiveData<Result<List<Note>>>()
    val noteList: LiveData<Result<List<Note>>> = _noteList

    init {
        getAllNotes()
    }


    fun insert(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
        getAllNotes()
    }

    fun getAllNotes() = viewModelScope.launch {
        _noteList.postValue(Result.loading(null))
        _noteList.postValue(Result.success(noteRepository.getAllNotes()))
    }


}