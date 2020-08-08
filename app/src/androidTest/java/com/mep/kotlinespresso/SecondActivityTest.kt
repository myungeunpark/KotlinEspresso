package com.mep.kotlinespresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SecondActivityTest{


    @get: Rule
    val activityRule = ActivityScenarioRule(SecondActivity::class.java)

    @Test
    fun TestActivityLaunch() {

        onView(withId(R.id.second_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun TestVisivilityViews() {

        onView(withId(R.id.secondTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.button2)).check(matches(isDisplayed()))
    }


    @Test
    fun TestCheckText() {

        onView(withId(R.id.secondTitle)).check(matches(withText(R.string.second_title)))
        onView(withId(R.id.button2)).check(matches(withText(R.string.second_back)))
    }
}


