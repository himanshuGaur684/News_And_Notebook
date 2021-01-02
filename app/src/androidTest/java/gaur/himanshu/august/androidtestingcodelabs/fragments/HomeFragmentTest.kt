package gaur.himanshu.august.androidtestingcodelabs.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.adapters.NoteAdapter
import gaur.himanshu.august.androidtestingcodelabs.fragmentfactory.HomeFragmentFactory
import gaur.himanshu.august.androidtestingcodelabs.launchFragmentInHiltContainer
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import gaur.himanshu.august.androidtestingcodelabs.repository.RepositoryInterface
import gaur.himanshu.august.androidtestingcodelabs.utils.insertNote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var homeFactory: HomeFragmentFactory

    @Inject
    lateinit var noteRepositoryInterface: RepositoryInterface

    @Before
    fun setUp() {
        hiltRule.inject()


    }

    @After
    fun tearDown() {
    }

    /**
     * Test our floating Action Button
     */
    @Test
    fun checkOurFloatingActionButton() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<HomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.add_notes)).perform(click())
        verify(navController).navigate(
            R.id.action_homeFragment_to_detailedFragment
        )
    }


    /**
     * Recycler view appear on screen
     */

    @Test
    fun test_ourRecyclerViewIsAppearOrNot() {

        launchFragmentInHiltContainer<HomeFragment> {
        }
        onView(withId(R.id.notes_recycler)).check(
            matches(isDisplayed())
        )
    }


    /**
     * Perfrom click and check the navigation of the details Fragment
     */
    @Test
    fun test_clickOnOurListItem() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<HomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.notes_recycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<NoteAdapter.MyViewHolder>(
                0,
                click()
            )
        )
    }

    /**
     * Check our saved list is appear on screen or not
     */

    @Test
    fun test_insertedItemIsAppearOnNotAfterInsertion() {
        val note1 = Note("Title 1", "Disc 1", 1)
        val note2 = Note("Title 2", "Disc 2", 2)

        noteRepositoryInterface.insertNote(note1)
        noteRepositoryInterface.insertNote(note2)
        launchFragmentInHiltContainer<HomeFragment> { }
        // Check Inserted Item is Appear or not
        onView(withText("Title 1")).check(matches(isDisplayed()))

    }


    @Test
    fun test_deletionItemInOurDatabase() {
        // Insert new Note in order to perform checking deletion
        val note3 = Note("Title 3", "Disc 3", 3)
        noteRepositoryInterface.insertNote(note3)
        // launch our fragment in hilt container
        launchFragmentInHiltContainer<HomeFragment> {  }
        // for second note 4  check our
        // perform long click
        onView(withText("Title 3")).perform(longClick())
        // check our dialog will appear or not
        onView(withText("Delete")).check(matches(isDisplayed()))
        // perform click to delete the object
        onView(withText("No")).perform(click())
        // check our snackbar is visible or not
        onView(withText("Cancel")).check(matches(isDisplayed()))

        // perform long click
        onView(withText("Title 3")).perform(longClick())
        // check our dialog will appear or not
        onView(withText("Delete")).check(matches(isDisplayed()))
        // perform click to delete the object
        onView(withText("Yes")).perform(click())
        // check our item is deleted or not
        onView(withText("Deleted Successfully")).check(matches(isDisplayed()))


    }

}