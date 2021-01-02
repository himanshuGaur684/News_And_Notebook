package gaur.himanshu.august.androidtestingcodelabs.repository

import android.util.Log
import gaur.himanshu.august.androidtestingcodelabs.Result
import gaur.himanshu.august.androidtestingcodelabs.Status
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.networks.NewsInterface
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import gaur.himanshu.august.androidtestingcodelabs.room.NoteDao
import gaur.himanshu.august.androidtestingcodelabs.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NoteRepository constructor(
    private val dao: NoteDao,
    private val newsInterface: NewsInterface
) : RepositoryInterface {

    override suspend fun insert(note: Note) {
        withContext(IO) {
            dao.insert(note)
        }

    }

    override suspend fun delete(note: Note) {
        dao.delete(note)
    }

    override suspend fun getAllNotes(): List<Note> {
        return dao.getAllNotes()
    }

    override suspend fun getAllNews(): Result<List<Article>> {

        return wrapEspressoIdlingResource {
            return@wrapEspressoIdlingResource try {
                val responce =
                    newsInterface.getAllNews(
                        "us",
                        "business",
                        "436a7b507ee5433bafa1ad67c8eff93b",
                        80
                    )
                Log.d("TAG", "getAllNews: ${responce.body()}")
                return if (responce.isSuccessful) {
                    Result(Status.SUCCESS, responce.body()!!.articles, null)
                } else {
                    Result.error("Error Occured", null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Result.error(e.message!!, null)
            }
        }

    }
}