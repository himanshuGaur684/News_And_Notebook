package gaur.himanshu.august.androidtestingcodelabs.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import gaur.himanshu.august.androidtestingcodelabs.CoroutineRule
import gaur.himanshu.august.androidtestingcodelabs.Status
import gaur.himanshu.august.androidtestingcodelabs.getOrAwaitValues
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.repository.FakeNoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NoteViewModelsTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NoteViewModels

    @Before
    fun setUp() {
        viewModel = NoteViewModels(FakeNoteRepository())
        val note = Note("Title", "Discription", 1)
        viewModel.insert(note)
    }

    @Test
    fun `insert note to db`() {
        viewModel.getAllNotes()
        assertThat(
            viewModel.noteList.getOrAwaitValues().status
        ).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `get all note is working or not`() {
        val note = Note("Title", "Discription", 1)
        viewModel.insert(note)
        viewModel.getAllNotes()
        assertThat(
            viewModel.noteList.getOrAwaitValues().data?.isNotEmpty()
        ).isTrue()
    }

    @Test
    fun `delete our content`() {
        val note = Note("Title", "Discription", 1)
        viewModel.delete(note)
        val value = viewModel.noteList.getOrAwaitValues()
        assertThat(
            value.status
        ).isEqualTo(Status.SUCCESS)
    }
    
    @Test
    fun `check our network calls`() {
        viewModel.getAllNews()
        val values = viewModel.newsList.getOrAwaitValues()
        assertThat(values.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }
}