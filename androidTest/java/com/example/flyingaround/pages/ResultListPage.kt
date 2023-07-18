package com.example.flyingaround.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.flyingaround.R
import org.hamcrest.Matchers.allOf
import android.R as android_R

object ResultListPage : Page<ResultListPage>() {

    private val recycler = onView(withId(R.id.resultListRecyclerView))

    private val toolbar = onView(withId(R.id.toolbarResult))
    private val emptyResultListTitle = onView(withId(R.id.emptyResultTextView))

    //widget info
    private val originTitle = onView(withId(R.id.originLabel))
    private val originCity = onView(withId(R.id.origin))
    private val destTitle = onView(withId(R.id.destinationLabel))
    private val destCity = onView(withId(R.id.destination))
    private val flightDateTitle = onView(withId(R.id.flightDateLabel))
    private val flightDateNumber = onView(withId(R.id.flightDate))
    private val infantsTitle = onView(withId(R.id.infantsLeftLabel))
    private val infantsNumber = onView(withId(R.id.infantsLeft))
    private val fareTitle = onView(withId(R.id.fareClassLabel))
    private val fareClass = onView(withId(R.id.fareClass))
    private val discountTitle = onView(withId(R.id.discountInPercentLabel))
    private val discountPercent = onView(withId(R.id.discountInPercent))

    private val cancelButton = onView(withId(android_R.id.button2))
    private val shareButton = onView(withId(android_R.id.button1))

    init {
        toolbar.check(matches(isDisplayed()))
    }

    fun assertToolbarDestinationTitle(destination: String) = apply {
        onView(allOf(withText(destination), withParent(withId(R.id.toolbarResult)))).check(matches(isDisplayed()))
    }

    fun assertResultListPageIsEmpty() = apply {
        emptyResultListTitle.check(matches(withText(R.string.search_result_is_empty)))
    }
}