package com.example.flyingaround.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.flyingaround.search.view.SearchActivity
import org.junit.Rule

open class BaseTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)
}