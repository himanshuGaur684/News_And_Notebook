package gaur.himanshu.august.androidtestingcodelabs.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@ExperimentalCoroutinesApi
@SmallTest
class NoteDatabaseTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("note_db")
    lateinit var noteDatabase: NoteDatabase

    lateinit var noteDao: NoteDao

    @Before
    fun setUp() {
        hiltRule.inject()
        noteDao = noteDatabase.getNoteDao()
    }

    @After
    fun tearDown() {
        noteDatabase.close()
    }

    @Test
    fun insert() = runBlockingTest {
        val item = Note("Title", "Description", 1)
        noteDao.insert(item)
        assertThat(noteDao.getAllNotes().isNotEmpty()).isTrue()
    }

    @Test
    fun delete() = runBlockingTest {
        val item1 = Note("Title", "Description", 1)
        val item2 = Note("Title", "Description", 2)
        noteDao.insert(item1)
        noteDao.insert(item2)
        noteDao.delete(item1)
        noteDao.delete(item2)
        assertThat(noteDao.getAllNotes().isEmpty()).isTrue()
    }

    
}