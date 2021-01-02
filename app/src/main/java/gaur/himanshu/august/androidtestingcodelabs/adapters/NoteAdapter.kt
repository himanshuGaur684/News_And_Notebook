package gaur.himanshu.august.androidtestingcodelabs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import gaur.himanshu.august.androidtestingcodelabs.BR
import gaur.himanshu.august.androidtestingcodelabs.databinding.ListItemForNotesBinding
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import kotlinx.android.synthetic.main.list_item_for_notes.view.*

class NoteAdapter(val noteClickListioners: NoteClickListioners) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    var list = mutableListOf<Note>()

    fun setContentList(list: List<Note>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }


    inner class MyViewHolder(val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.MyViewHolder {
        val binding =
            ListItemForNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.MyViewHolder, position: Int) {

        holder.viewDataBinding.setVariable(BR.note, list[position])


        holder.viewDataBinding.root.list_item_note_root.setOnClickListener {
            noteClickListioners.clickHandler(list[position], position)
        }

        holder.viewDataBinding.root.list_item_note_root.setOnLongClickListener {
            noteClickListioners.deleteListItem(list[position], position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}