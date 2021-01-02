package gaur.himanshu.august.androidtestingcodelabs.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.R
import gaur.himanshu.august.androidtestingcodelabs.Status
import gaur.himanshu.august.androidtestingcodelabs.fragmentfactory.HomeFragmentFactory
import gaur.himanshu.august.androidtestingcodelabs.launchFragmentInHiltContainer
import gaur.himanshu.august.androidtestingcodelabs.repository.FakeNoteRepositoryAndroidTest
import gaur.himanshu.august.androidtestingcodelabs.repository.RepositoryInterface
import gaur.himanshu.august.androidtestingcodelabs.utils.EspressoIdlingResource
import gaur.himanshu.august.androidtestingcodelabs.utils.getAllNewss
import gaur.himanshu.august.androidtestingcodelabs.viewmodels.NoteViewModels
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var viewModels: NoteViewModels


    @Inject
    lateinit var repositoryInterface: RepositoryInterface

    @Inject
    lateinit var homeFragmentFactory: HomeFragmentFactory


    @Before
    fun setUp() {
        hiltRule.inject()
        viewModels = NoteViewModels(FakeNoteRepositoryAndroidTest())
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_recyclerIsAppearOrNot() {
        launchFragmentInHiltContainer<NewsFragment> {}
        onView(withId(R.id.news_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun test_networkRequest() {
        val navController = mock(NavController::class.java)
        val result = repositoryInterface.getAllNewss()
        launchFragmentInHiltContainer<NewsFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        when (result.status) {
            Status.SUCCESS -> {
                Thread.sleep(500)
                onView(withId(R.id.news_progress)).check(matches(not(isDisplayed())))
                onView(withId(R.id.news_recycler)).check(matches(isDisplayed()))
            }
            Status.ERROR -> {
                onView(withId(R.id.news_progress)).check(matches(not(isDisplayed())))
                onView(withText("Error Occurred")).check(matches(isDisplayed()))

            }
            Status.LOADING -> {
                onView(withId(R.id.news_progress)).check(matches(isDisplayed()))
            }
        }


    }
}