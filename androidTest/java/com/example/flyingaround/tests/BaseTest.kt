package com.example.flyingaround.tests

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.flyingaround.search.view.SearchActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

}