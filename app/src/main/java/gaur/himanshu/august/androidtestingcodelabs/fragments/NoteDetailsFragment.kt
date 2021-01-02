package gaur.himanshu.august.androidtestingcodelabs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.databinding.FragmentNoteDetailsBinding
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import kotlinx.android.synthetic.main.fragment_note_details.view.*

@AndroidEntryPoint
class NoteDetailsFragment : Fragment() {


    val args:NoteDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding=FragmentNoteDetailsBinding.inflate(inflater,container,false)



       if(args.notes!=null){
           val m =args.notes
           binding.detailsTitle.text = m!!.title
           binding.detailsDiscription.text = m.disc
       }


        return binding.root
    }



}