package gaur.himanshu.august.androidtestingcodelabs.fragments

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.MainActivity
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.launchFragmentInHiltContainer
import gaur.himanshu.august.androidtestingcodelabs.repository.FakeNoteRepositoryAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class LoginFragmentAndroidTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)


    lateinit var viewModel: NoteViewModels

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = NoteViewModels(FakeNoteRepositoryAndroidTest())
    }

    @After
    fun tearDown() {
    }


    @Test
    fun testLoginButtonPressedAction() {
        val navController = mock(NavController::class.java)

    //    launchFragmentInHiltContainer<LoginFragment>(Bundle(), R.style.Theme_AppCompat)
        launchFragmentInHiltContainer<LoginFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.login_button)).perform(click())
        verify(navController).navigate(
            R.id.action_loginFragment_to_homeFragment
        )
    }

    @Test
    fun test_onLaunchSavingActivityIsAppearOrNot(){
        // launch activity
        launchActivity()
        // check visibility of login
        onView(withId(R.id.user_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.pass_word)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.phone_number)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


    fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity->
        }
        return activityScenario
    }



}