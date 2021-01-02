package gaur.himanshu.august.androidtestingcodelabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T : Any> CoroutineScope.ioThenMain(work: suspend (() -> T), callback: (T?) -> Unit) = launch {
    withContext(Main) {
        val data = withContext(IO) {
            async { return@async work() }.await()
        }
        callback(data)
    }
}


fun <T : Any> ViewModel.ioThenMain(work: suspend (() -> T), callback: (T?) -> Unit) =
    viewModelScope.ioThenMain(work, callback)