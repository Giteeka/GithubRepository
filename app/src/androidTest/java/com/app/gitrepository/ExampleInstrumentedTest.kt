package com.app.gitrepository

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.app.gitrepository.ui.home.HomeActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(HomeActivity::class.java, true, false)

    private val mockWebServer = MockWebServer()

    private var progressBarGoneIdlingResource: ApiIdlingResource? = null

    private lateinit var recyclerView: View
    private lateinit var btnRetry: View
    private lateinit var activity: HomeActivity

    @Before
    fun setup() {
        mockWebServer.start(BuildConfig.PORT.toInt())
//        mockWebServer.
    }

    @Test
    fun displayList() {
//        mockWebServer.start(BuildConfig.PORT.toInt())
        mockWebServer.dispatcher = SuccessDispatcher()
        activityTestRule.launchActivity(null)
        activity = activityTestRule.activity
        recyclerView = activity.findViewById(R.id.rv_items)
        progressBarGoneIdlingResource =
            ApiIdlingResource(recyclerView , View.VISIBLE)
        IdlingRegistry.getInstance().register(progressBarGoneIdlingResource)
        Espresso.onView(ViewMatchers.withId(recyclerView.id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun errorView() {
//        mockWebServer.start(BuildConfig.PORT.toInt())
        mockWebServer.dispatcher = ErrorDispatcher()
        activityTestRule.launchActivity(null)
        activity = activityTestRule.activity
        btnRetry= activity.findViewById(R.id.btn_retry)
        progressBarGoneIdlingResource =
            ApiIdlingResource(btnRetry , View.VISIBLE)
        IdlingRegistry.getInstance().register(progressBarGoneIdlingResource)
        Espresso.onView(ViewMatchers.withId(btnRetry.id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(progressBarGoneIdlingResource)
    }

}
