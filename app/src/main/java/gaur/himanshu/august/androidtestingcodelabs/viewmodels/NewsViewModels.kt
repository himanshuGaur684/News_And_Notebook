package gaur.himanshu.august.androidtestingcodelabs.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gaur.himanshu.august.androidtestingcodelabs.Events
import gaur.himanshu.august.androidtestingcodelabs.Result
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import gaur.himanshu.august.androidtestingcodelabs.repository.NoteRepository
import gaur.himanshu.august.androidtestingcodelabs.repository.RepositoryInterface
import kotlinx.coroutines.launch

class NewsViewModels @ViewModelInject constructor(
    private val noteRepository: RepositoryInterface
): ViewModel() {


    private val _newsList = MutableLiveData<Events<Result<List<Article>>>>()
    val newsList: LiveData<Events<Result<List<Article>>>> = _newsList

    init {
        getAllNews()
    }

    fun getAllNews() = viewModelScope.launch {

        _newsList.postValue(Events(Result.loading(null)))
        _newsList.postValue(Events(noteRepository.getAllNews()))


    }
}