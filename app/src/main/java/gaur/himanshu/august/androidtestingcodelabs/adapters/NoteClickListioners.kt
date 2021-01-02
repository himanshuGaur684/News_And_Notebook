package gaur.himanshu.august.androidtestingcodelabs.adapters

import gaur.himanshu.august.androidtestingcodelabs.model.Note

interface NoteClickListioners {

    fun clickHandler(note: Note, position: Int)

    fun deleteListItem(note: Note,position: Int)

}