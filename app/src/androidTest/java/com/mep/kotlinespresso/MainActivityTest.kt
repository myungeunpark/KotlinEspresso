package com.mep.kotlinespresso

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.AllOf.allOf

import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(SecondActivity::class.java)

    @get:Rule
    val intentRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun TestLaunchMainActivity() {
        // main activity visibility
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun TestVisibilityView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.titleText)).check(matches(isDisplayed()))
        onView(withId(R.id.button)).check(matches(isDisplayed()))
    }

    @Test
    fun TestStartSecondActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.second_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.locale)).check(matches(withText(R.string.locale_korea)))
    }


    @Test
    fun TestBackToMainActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.second_activity)).check(matches(isDisplayed()))
        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun TestCheckViewText() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.titleText)).check(matches(withText(R.string.main_title)))
        onView(withId(R.id.button)).check(matches(withText(R.string.main_button)))
    }


    @Test
    fun TestCountryCodeWithNoIntent() {
        // start second activity with no intent data
        activityRule.launchActivity(Intent())
        onView(withId(R.id.locale)).check(matches(withText(R.string.locale_no)))
    }

    @Test
    fun TestCountryCodeWithIntentUSA() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val countryUsa = context.resources.getString(R.string.country_usa)

        // intent data passing and display
        val intent = Intent()
        intent.putExtra(SecondActivity.COUNTRY, countryUsa)
        activityRule.launchActivity(intent)

        onView(withId(R.id.locale)).check(matches(withText(R.string.locale_usa)))
    }

    @Test
    fun TestCountryCodeWithIntentKorea() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val countryKorea = context.resources.getString(R.string.country_korea)

        // intent data passing and display
        val intent = Intent()
        intent.putExtra(SecondActivity.COUNTRY, countryKorea)
        activityRule.launchActivity(intent)

        onView(withId(R.id.locale)).check(matches(withText(R.string.locale_korea)))
    }

    @Test
    fun TestIntended(){

        //val intentRule = IntentsTestRule(MainActivity::class.java)
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button)).perform(click())

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val countryKorea = context.resources.getString(R.string.country_korea)

        intended(hasExtra(SecondActivity.COUNTRY, countryKorea))
        intended(toPackage("com.mep.kotlinespresso"))
        intended(hasComponent("com.mep.kotlinespresso.SecondActivity"))

        intended(allOf(
            hasExtra(SecondActivity.COUNTRY, countryKorea),
            toPackage("com.mep.kotlinespresso"),
            hasComponent("com.mep.kotlinespresso.SecondActivity")))

    }

    @Test
    fun TestIntending(){

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val localeText = context.getString(R.string.locale_korea)

        val intent = Intent()
        intent.putExtra(SecondActivity.CODE, localeText)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)

        intending(toPackage("com.mep.kotlinespresso")).respondWith(result)

        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.result_code)).check(matches(withText(R.string.locale_korea)))
    }

    @Test
    fun TestEditInput(){

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val input = context.resources.getString(R.string.country_canada)

        onView(withId(R.id.editText)).perform(typeText(input), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())

        onView(withId(R.id.locale)).check(matches(withText(R.string.locale_canada)))

        onView(withId(R.id.button2)).perform(click())
        onView(withId(R.id.result_code)).check(matches(withText(R.string.locale_canada)))
    }
}