package gaur.himanshu.august.androidtestingcodelabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.androidtestingcodelabs.Constants
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModels
import kotlinx.android.synthetic.main.fragment_detailed.view.*

@AndroidEntryPoint
class AddNoteFragment : Fragment() {


    private val viewModel by activityViewModels<NoteViewModels>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.save_note.setOnClickListener {
            val title = view.title_text.editText?.text?.trim().toString()
            val description = view.title_disc.editText?.text?.trim().toString()
            val check = checkEntry(title, description)
            if (check == null) {
                viewModel.insert(Note(title, description, 0))
                viewModel.getAllNotes()
                findNavController().popBackStack()
            } else {
                Snackbar.make(requireView(), check.toString(), Snackbar.LENGTH_SHORT).show()
            }


        }


    }

     fun checkEntry(title: String?, desc: String?): String? {
        if (title.toString().trim().isEmpty() || desc.toString().trim().isEmpty()) {
            return "Please enter Title and Description"
        }
        if (title.toString().trim().length > Constants.TITLE_LENGTH) {
            return "Title length is less than 30 letters"
        }

        if (desc.toString().trim().length > Constants.DESC_LENGTH) {
            return "Desc length is less than 300 letters"
        }
        return null
    }

}