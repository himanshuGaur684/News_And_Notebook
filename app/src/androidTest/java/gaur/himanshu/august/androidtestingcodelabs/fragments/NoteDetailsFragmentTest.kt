package gaur.himanshu.august.androidtestingcodelabs.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.launchFragmentInHiltContainer
import gaur.himanshu.august.androidtestingcodelabs.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
class NoteDetailsFragmentTest {


    @get:Rule
    val hiltRule=HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }


    @Test
    fun test_noteDetailFragmentDataIsAppearOrNot(){

        val note= Note("Title 1","Discription 1",1)

        val bundle=NoteDetailsFragmentArgs(note).toBundle()

        launchFragmentInHiltContainer<NoteDetailsFragment>(bundle, R.style.Theme_AppCompat)

        onView(withId(R.id.details_title)).check(matches(withText("Title 1")))

    }

}