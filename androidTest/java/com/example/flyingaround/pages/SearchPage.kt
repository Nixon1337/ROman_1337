package com.example.flyingaround.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.flyingaround.R
import com.example.flyingaround.resultlist.view.ResultListActivity
import com.example.flyingaround.search.view.SearchActivity
import com.example.flyingaround.utils.Page
import com.example.flyingaround.utils.HelperMethods.isDisplayed
import android.R as android_R
import org.hamcrest.core.Is.`is`
import org.hamcrest.CoreMatchers.*

object SearchPage : Page<SearchPage>() {

    private var mActivity: SearchActivity? = null
    private val device = UiDevice.getInstance(getInstrumentation())

    private val container = onView(withId(R.id.formContainer))
    private val toolbar = onView(withId(R.id.toolbar))
    private val originStationField = onView(withId(R.id.originAutoCompleteTextView))
    private val destStationField = onView(withId(R.id.destinationAutoCompleteTextView))
    private val departureField = onView(withId(R.id.departureEditText))
    private val adultsField = onView(withId(R.id.adultsEditText))
    private val teensField = onView(withId(R.id.teensEditText))
    private val childrenField = onView(withId(R.id.childrenEditText))
    private val searchButton = onView(withId(R.id.searchButton))

    // Calendar
    private val calendar = device.findObject(UiSelector().resourceId("android:id/parentPanel"))
    private val monthGrid = device.findObject(UiSelector().resourceId("android:id/month_view"))
    private val nextMonthButton = device.findObject(UiSelector().resourceId("android:id/next"))
    private val okButton = onView(withId(android_R.id.button1)).inRoot(isDialog())
    private val cancelButton = onView(withId(android_R.id.button2)).inRoot(isDialog())


    init {
        toolbar.isDisplayed()
        container.isDisplayed()
        originStationField.check(matches(withHint("Origin station")))
        destStationField.check(matches(withHint("Destination station")))
        departureField.check(matches(withHint("Departure")))
        adultsField.check(matches(withHint("Number of adults")))
        teensField.check(matches(withHint("Number of teens")))
        childrenField.check(matches(withHint("Number of children")))
        searchButton.check(matches(withText(R.string.search)))
    }

    fun typeInOriginStationField(city: String, autocompleteStation: String) = apply {
        originStationField.perform(typeText(city)).isDisplayed()
        chooseAutocompleteStation(autocompleteStation)
    }

    fun typeInDestStationField(city: String, autocompleteStation: String) = apply {
        destStationField.perform(typeText(city)).isDisplayed()
        chooseAutocompleteStation(autocompleteStation)
    }

    fun tapDepartureField() = apply {
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
        Intents.init()
        searchButton.perform(click())
        intended(hasComponent(ResultListActivity::class.java.name))
    }

    fun chooseDayInCalendar(day: String) = apply {
        monthGrid.getChild(UiSelector().className("android.view.View").textMatches(day)).click()
    }

    fun tapNextMonthInCalendar() = apply {
        nextMonthButton.click()
    }

    fun tapOkButton() = apply {
        okButton.perform(click())
    }

    fun assertDepartureField(date: String) {
        departureField.check(matches(withText(date)))
    }

    private fun chooseAutocompleteStation(autocompleteStation: String) = apply {
        onView(withText(autocompleteStation))
            .inRoot(withDecorView(not(`is`(mActivity?.window?.decorView)))).isDisplayed().perform(click())
    }
}