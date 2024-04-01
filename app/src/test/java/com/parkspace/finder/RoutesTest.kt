package com.parkspace.finder

import com.parkspace.finder.navigation.ROUTE_ACCOUNT
import com.parkspace.finder.navigation.ROUTE_BOOKINGS
import com.parkspace.finder.navigation.ROUTE_BOOKING_DETAIL
import com.parkspace.finder.navigation.ROUTE_BOOKING_SERVICE
import com.parkspace.finder.navigation.ROUTE_BROWSE
import com.parkspace.finder.navigation.ROUTE_CONFIRM_BOOKING_DETAILS
import com.parkspace.finder.navigation.ROUTE_ENTER_BOOKING_DETAIL_SCREEN
import com.parkspace.finder.navigation.ROUTE_FAVORITES
import com.parkspace.finder.navigation.ROUTE_FILTER
import com.parkspace.finder.navigation.ROUTE_HOME
import com.parkspace.finder.navigation.ROUTE_LOGIN
import com.parkspace.finder.navigation.ROUTE_NOTIFICATIONS
import com.parkspace.finder.navigation.ROUTE_ONBOARDING
import com.parkspace.finder.navigation.ROUTE_PARKING_DETAIL
import com.parkspace.finder.navigation.ROUTE_PARKING_TICKET
import com.parkspace.finder.navigation.ROUTE_PARKING_TIMER
import com.parkspace.finder.navigation.ROUTE_PAYMENT
import com.parkspace.finder.navigation.ROUTE_PAYMENT_SUCCESS
import com.parkspace.finder.navigation.ROUTE_REQUEST_LOCATION_PERMISSION
import com.parkspace.finder.navigation.ROUTE_SEARCH
import com.parkspace.finder.navigation.ROUTE_SIGNUP
import org.junit.Assert.assertEquals
import org.junit.Test

class RoutesTest {

    @Test
    fun testRoutes() {
        assertEquals("login", ROUTE_LOGIN)
        assertEquals("signup", ROUTE_SIGNUP)
        assertEquals("home", ROUTE_HOME)
        assertEquals("parking/{parkingId}/pay", ROUTE_PAYMENT)
        assertEquals("booking/{bookingId}/success", ROUTE_PAYMENT_SUCCESS)
        assertEquals("booking_service", ROUTE_BOOKING_SERVICE)
        assertEquals("parking/{parkingId}/book", ROUTE_ENTER_BOOKING_DETAIL_SCREEN)
        assertEquals("parking/{parkingId}/confirm", ROUTE_CONFIRM_BOOKING_DETAILS)
        assertEquals("booking/{bookingId}/ticket", ROUTE_PARKING_TICKET)
        assertEquals("booking/{bookingId}/timer", ROUTE_PARKING_TIMER)
        assertEquals("browse", ROUTE_BROWSE)
        assertEquals("bookings", ROUTE_BOOKINGS)
        assertEquals("booking/{bookingId}", ROUTE_BOOKING_DETAIL)
        assertEquals("favorites", ROUTE_FAVORITES)
        assertEquals("notifications", ROUTE_NOTIFICATIONS)
        assertEquals("account", ROUTE_ACCOUNT)
        assertEquals("parking/{parkingId}", ROUTE_PARKING_DETAIL)
        assertEquals("onboarding", ROUTE_ONBOARDING)
        assertEquals("request_location_permission", ROUTE_REQUEST_LOCATION_PERMISSION)
        assertEquals("filter", ROUTE_FILTER)
        assertEquals("search", ROUTE_SEARCH)
    }
}
