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
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import gaur.himanshu.august.androidtestingcodelabs.networks.response.Source
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
@MediumTest
class NewsDetailsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_budleSendIsWorkingOnNewsDetailsFrag() {

        val article = Article(
            "author 1", "content 1", "discription 1", "published 1",
            Source("id", "name"), "title 1", "url", "https://images.mktw.net/im-278767/social"
        )
        val bundles = NewsDetailsFragmentArgs(article).toBundle()
        launchFragmentInHiltContainer<NewsDetailsFragment>(fragmentsArgs = bundles, themeResId = R.style.Theme_AppCompat)
        onView(withId(R.id.news_details_title)).check(matches(withText("title 1")))

    }


}