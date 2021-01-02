package gaur.himanshu.august.androidtestingcodelabs.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.Status
import gaur.himanshu.august.androidtestingcodelabs.adapters.NoteAdapter
import gaur.himanshu.august.androidtestingcodelabs.adapters.NoteClickListioners
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModels
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment(), NoteClickListioners {

    private val viewModel by activityViewModels<NoteViewModels>()

    private val noteAdapter = NoteAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.add_notes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailedFragment)
        }

        view.go_to_news.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
        }



        viewModel.noteList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    noteAdapter.setContentList(it.data!!)
                }
                Status.ERROR -> {
                }

                Status.LOADING -> {
                }
            }
        }
        view.notes_recycler.also {
            it.adapter = noteAdapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun clickHandler(note: Note, position: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToNoteDetailsFragment(
                note
            )
        )
    }

    override fun deleteListItem(note: Note, position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.delete(note)
                Snackbar.make(requireView(),"Deleted Successfully",Snackbar.LENGTH_SHORT).show()

            }
            .setNegativeButton("No") { _, _ ->
                Snackbar.make(requireView(),"Cancel",Snackbar.LENGTH_SHORT).show()
            }
            .show()
    }

}