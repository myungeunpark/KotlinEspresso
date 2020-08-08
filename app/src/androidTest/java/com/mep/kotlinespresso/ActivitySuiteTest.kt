package com.mep.kotlinespresso

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class, SecondActivityTest::class)
class ActivitySuiteTest {
}