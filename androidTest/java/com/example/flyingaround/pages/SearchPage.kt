package com.example.flyingaround.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import java.util.Calendar
import com.example.flyingaround.R
import com.example.flyingaround.search.view.SearchActivity
import java.lang.Thread.sleep
import android.R as android_R
import org.hamcrest.core.Is.`is`
import org.hamcrest.CoreMatchers.*

object SearchPage : Page<SearchPage>() {

    private var mActivity: SearchActivity? = null

    private val toolbar = onView(withId(R.id.toolbar))

    private val originStationField = onView(withId(R.id.originAutoCompleteTextView))

    private val destStationField = onView(withId(R.id.destinationAutoCompleteTextView))
    private val departureField = onView(withId(R.id.departureEditText))
    private val adultsField = onView(withId(R.id.adultsEditText))
    private val teensField = onView(withId(R.id.teensEditText))
    private val childrenField = onView(withId(R.id.childrenEditText))

    private val searchButton = onView(withId(R.id.searchButton))

    private val device = UiDevice.getInstance(getInstrumentation())

    private val okButton = onView(withId(android_R.id.button1)).inRoot(isDialog())
    private val cancelButton = onView(withId(android_R.id.button2)).inRoot(isDialog())

    private val calendar = device.findObject(UiSelector().resourceId("android:id/parentPanel"))
    private val monthGrid = device.findObject(UiSelector().resourceId("android:id/month_view"))

    private val nextMonthButton = device.findObject(UiSelector().resourceId("android:id/next"))

    init {
        sleep(5_000)
        toolbar.check(matches(isDisplayed()))
        originStationField.check(matches(withHint("Origin station")))
        destStationField.check(matches(withHint("Destination station")))
        departureField.check(matches(withHint("Departure")))
        adultsField.check(matches(withHint("Number of adults")))
        teensField.check(matches(withHint("Number of teens")))
        childrenField.check(matches(withHint("Number of children")))
        searchButton.check(matches(withText(R.string.search)))
    }

    fun tapDepartureField() {
        departureField.perform(click())
        calendar.waitForExists(10_000)
        monthGrid.waitForExists(10_000)
        okButton.check(matches(isEnabled()))
        cancelButton.check(matches(isEnabled()))
    }

    fun typeInNumberOfAdultsField(adults: Int) = apply {
        adultsField.perform(typeText("$adults"))
        adultsField.check(matches(withText("$adults")))
    }

    fun typeInNumberOfTeensField(teens: Int) = apply {
        teensField.perform(typeText("$teens"))
        teensField.check(matches(withText("$teens")))
    }
    fun typeInNumberOfChildrenField(children: Int) = apply {
        childrenField.perform(typeText("$children"))
        childrenField.check(matches(withText("$children")))
    }

    fun tapSearchButton() {
        searchButton.perform(click())
    }

    fun chooseDayInCalendar(day: Int) {
        monthGrid.getChild(UiSelector().className("android.view.View").textMatches("$day")).click()
    }

    fun chooseMonthInCalendar() {
        val c: Calendar = Calendar.getInstance()
        val currentMonth = c.get(Calendar.MONTH)
        withText(currentMonth).matches(isDisplayed())
        nextMonthButton.click()
        sleep(3000)
        val nextMonth = c.get(Calendar.MONTH) + 1
        withText(nextMonth).matches(isDisplayed())
    }

    fun tapOkButton() {
        okButton.perform(click())
        sleep(2_000)
        //okButton.check(matches(not(isDisplayed())))
        //cancelButton.check(matches(not(isDisplayed())))
    }

    fun typeInOriginStationField(city: String, autocompleteStation: String) = apply {
        originStationField.perform(typeText(city)).check(matches(isDisplayed()))
        sleep(2_000)
        chooseAutocompleteStation(autocompleteStation)
    }

    fun typeInDestStationField(city: String, autocompleteStation: String) = apply {
        destStationField.perform(typeText(city)).check(matches(isDisplayed()))
        sleep(2_000)
        chooseAutocompleteStation(autocompleteStation)
    }

    private fun chooseAutocompleteStation(autocompleteStation: String) = apply {
        onView(withText(autocompleteStation))
            .inRoot(withDecorView(not(`is`(mActivity?.window?.decorView)))).perform(click())
    }
}