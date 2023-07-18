package com.example.flyingaround.tests

import com.example.flyingaround.pages.ResultListPage
import com.example.flyingaround.pages.SearchPage
import com.example.flyingaround.utils.CitiesData.THESSALONIKI
import com.example.flyingaround.utils.CitiesData.THESSALONIKI_STATION
import com.example.flyingaround.utils.CitiesData.WROCLAW
import com.example.flyingaround.utils.CitiesData.WROCLAW_STATION
import com.example.flyingaround.utils.CitiesData.WRO_TO_SKG
import org.junit.Test

class FlyingAroundTests : BaseTest() {

    @Test
    fun verifySearchResultIsEmpty() {
        SearchPage {
            typeInOriginStationField(WROCLAW, WROCLAW_STATION)
            typeInDestStationField(THESSALONIKI, THESSALONIKI_STATION)
            tapDepartureField()
            tapOkButton()
            typeInNumberOfAdultsField(2)
            tapSearchButton()
        }
        ResultListPage {
            assertToolbarDestinationTitle(WRO_TO_SKG)
            assertResultListPageIsEmpty()
        }
    }
}