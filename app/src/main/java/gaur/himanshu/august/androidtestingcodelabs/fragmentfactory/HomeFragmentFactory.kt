package gaur.himanshu.august.androidtestingcodelabs.fragmentfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import gaur.himanshu.august.androidtestingcodelabs.fragments.AddNoteFragment
import gaur.himanshu.august.androidtestingcodelabs.fragments.NoteDetailsFragment
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import javax.inject.Inject

class HomeFragmentFactory @Inject constructor() : FragmentFactory(){


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            NoteDetailsFragment::class.java.name->{
                val noteDetailsFragment=NoteDetailsFragment()

                noteDetailsFragment
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }

    }

}