package com.example.flyingaround.tests


import com.example.flyingaround.pages.ResultListPage
import com.example.flyingaround.pages.SearchPage
import com.example.flyingaround.utils.DateData.currentDate
import com.example.flyingaround.utils.DateData.currentDay
import com.example.flyingaround.utils.DateData.nextMonthDate
import com.example.flyingaround.utils.DateData.properFullDate
import com.example.flyingaround.utils.DateData.properShortestDate
import com.example.flyingaround.utils.SearchData.LONDON
import com.example.flyingaround.utils.SearchData.LONDON_STANSTED
import com.example.flyingaround.utils.SearchData.LONDON_STANSTED_STATION
import com.example.flyingaround.utils.SearchData.THESSALONIKI
import com.example.flyingaround.utils.SearchData.THESSALONIKI_STATION
import com.example.flyingaround.utils.SearchData.WROCLAW
import com.example.flyingaround.utils.SearchData.WROCLAW_STATION
import com.example.flyingaround.utils.SearchData.WRO_TO_SKG
import com.example.flyingaround.utils.SearchData.WRO_TO_STN
import org.junit.Before
import org.junit.Test
import java.lang.Thread.sleep

class FlyingAroundTests : BaseTest() {

    @Before
    fun beforeEachTest() {
        SearchPage {
            typeInOriginStationField(WROCLAW, WROCLAW_STATION)
        }
    }

    @Test
    fun verifySearchResultIsEmpty() {
        SearchPage {
            typeInDestStationField(THESSALONIKI, THESSALONIKI_STATION)
            tapDepartureField()
            tapOkButton()
            assertDepartureField(currentDate)
            typeInNumberOfAdultsField(2)
            tapSearchButton()
        }
        ResultListPage {
            assertToolbarDestinationTitle(WRO_TO_SKG)
            assertResultListPageIsEmpty()
        }
    }

    @Test
    fun verifyLondon() {
        SearchPage {
            typeInDestStationField(LONDON, LONDON_STANSTED_STATION)
            tapDepartureField()
            tapNextMonthInCalendar()
            chooseDayInCalendar("27")
            tapOkButton()
            assertDepartureField(properShortestDate)
            typeInNumberOfAdultsField(1)
            typeInNumberOfTeensField(1)
            typeInNumberOfChildrenField(1)
            tapSearchButton()
        }
        ResultListPage {
            assertToolbarDestinationTitle(WRO_TO_STN)
            tapFlight()
            assertPopUp(WROCLAW, LONDON_STANSTED, properFullDate)
        }
    }
}