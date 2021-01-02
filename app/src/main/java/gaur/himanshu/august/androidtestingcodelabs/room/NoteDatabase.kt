package gaur.himanshu.august.androidtestingcodelabs.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gaur.himanshu.august.androidtestingcodelabs.model.Note

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    companion object {

        fun get(context: Context) = Room.databaseBuilder(context, NoteDatabase::class.java, "Room")
            .build()

    }

    abstract fun getNoteDao():NoteDao

}