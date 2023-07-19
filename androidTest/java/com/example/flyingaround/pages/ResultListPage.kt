package com.example.flyingaround.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.example.flyingaround.R
import com.example.flyingaround.utils.DateData.departureNextMonth
import com.example.flyingaround.utils.DateData.properFullDate
import com.example.flyingaround.utils.DateData.properShortDate
import com.example.flyingaround.utils.HelperMethods.getText
import com.example.flyingaround.utils.Page
import com.example.flyingaround.utils.HelperMethods.isDisplayed
import org.hamcrest.Matchers.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import android.R as android_R

object ResultListPage : Page<ResultListPage>() {

    private val recycler = onView(withId(R.id.resultListRecyclerView))

    private val toolbar =  onView(withId(R.id.toolbarResult))
    private val emptyResultListTitle = onView(withId(R.id.emptyResultTextView))

    // Widget info
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
        toolbar.isDisplayed()
    }

    fun assertToolbarDestinationTitle(destination: String) = apply {
        onView(allOf(withText(destination), withParent(withId(R.id.toolbarResult)))).isDisplayed()
    }

    fun assertResultListPageIsEmpty() = apply {
        emptyResultListTitle.check(matches(withText(R.string.search_result_is_empty)))
    }

    fun scrollToItemWithText() {
        val flightTime = onView(withId(R.id.flightTime))
        val searchText = getText(flightTime)
        val stringArray = searchText.split(":")
        val intArray = stringArray.map { it.toInt() }.toTypedArray()
        recycler.isDisplayed()
        for (i in 1..5) {
            if (intArray[0] <= 16) {
                recycler.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(i))
            }
        }
    }

    fun tapFlight() {
        recycler.isDisplayed()
        UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(UiSelector().text(properShortDate))
        val flightTime = onView(allOf(withId(R.id.flightTime), hasSibling(
            allOf(withId(R.id.flightDate), withText(properShortDate)))))
        val searchText = getText(flightTime)
        val stringArray = searchText.split(":")
        val intArray = stringArray.map { it.toInt() }.toTypedArray()
        val time = LocalTime.of(intArray[0],intArray[1], intArray[2])
        val formatter2 = DateTimeFormatter.ofPattern("HH")
        val flightTimeTrue = formatter2.format(time)
        if (Integer.valueOf(flightTimeTrue) > 12) {
            onView(allOf(withId(R.id.flightTime), hasSibling(
                allOf(withId(R.id.flightDate), withText(properShortDate))))).perform(click())
        }
    }

    fun assertPopUp(cityOfOrigin: String, cityOfDestination: String, dateAndTime: String) {
        originTitle.check(matches(withText(R.string.origin)))
        originCity.check(matches(withText(cityOfOrigin)))
        destTitle.check(matches(withText(R.string.destination)))
        destCity.check(matches(withText(cityOfDestination)))
        flightDateTitle.check(matches(withText(R.string.flightDate)))
        flightDateNumber.check(matches(withText(dateAndTime)))
        infantsTitle.check(matches(withText(R.string.infants_left)))
        infantsNumber.check(matches(isDisplayed()))
        fareTitle.check(matches(withText(R.string.fare_class)))
        fareClass.check(matches(isDisplayed()))
        discountTitle.check(matches(withText(R.string.discount)))
        discountPercent.check(matches(isDisplayed()))
        cancelButton.check(matches(isEnabled()))
        shareButton.check(matches(isEnabled()))
    }
}
