package gaur.himanshu.august.androidtestingcodelabs.room

import androidx.lifecycle.LiveData
import androidx.room.*
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM Note")
    suspend fun getAllNotes(): List<Note>

}