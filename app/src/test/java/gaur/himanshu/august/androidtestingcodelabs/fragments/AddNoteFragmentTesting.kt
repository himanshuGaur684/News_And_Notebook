package gaur.himanshu.august.androidtestingcodelabs.fragments


import com.google.common.truth.Truth.assertThat
import gaur.himanshu.august.androidtestingcodelabs.Constants
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModelsTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.StringBuilder

@ExperimentalCoroutinesApi
class AddNoteFragmentTesting {

    lateinit var viewModels: NoteViewModelsTest
    lateinit var addNoteFragment: AddNoteFragment

    @Before
    fun setUp() {
        viewModels = NoteViewModelsTest()
        addNoteFragment = AddNoteFragment()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test insertion of the note if title or discription is empty`() {
        assertThat(
            addNoteFragment.checkEntry(
                " ",
                " "
            )
        ).isEqualTo("Please enter Title and Description")
    }


    @Test
    fun `test insertion of the note if title length greater than 10`() {
        val str=StringBuilder()
            for(i in 0..(Constants.TITLE_LENGTH+1)){
         str.append("$i")
     }
        assertThat(
            addNoteFragment.checkEntry(
                str.toString(),
                "Discription"
            )
        ).isEqualTo("Title length is less than 30 letters")
    }


    @Test
    fun `test insertion of the note if description length greater than 15`() {
        val str=StringBuilder()
        for(i in 0..(Constants.DESC_LENGTH+1)){
            str.append("$i")
        }
        assertThat(
            addNoteFragment.checkEntry(
                "Title"
                ,str.toString()
            )
        ).isEqualTo("Desc length is less than 300 letters")
    }

    @Test
    fun `test insertion when everything is perfect`() {
       assertThat(
            addNoteFragment.checkEntry(
                "Title"
                ,"Discription"
            )
        ).isEqualTo(null)
    }


}