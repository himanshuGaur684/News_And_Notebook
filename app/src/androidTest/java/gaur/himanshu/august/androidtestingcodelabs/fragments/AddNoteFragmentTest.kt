package gaur.himanshu.august.androidtestingcodelabs.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.launchFragmentInHiltContainer
import gaur.himanshu.august.androidtestingcodelabs.repository.FakeNoteRepositoryAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
class AddNoteFragmentTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var viewModels: NoteViewModels

    @Before
    fun setup() {
        hiltRule.inject()
        viewModels = NoteViewModels(FakeNoteRepositoryAndroidTest())
    }

    @Test
    fun checkSaveFloatingActionButton() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddNoteFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.save_note)).perform(click())
        verify(navController).popBackStack()
    }

}