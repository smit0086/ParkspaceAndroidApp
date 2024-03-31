package com.parkspace.finder.data

interface BookingDetailRepository {
    suspend fun makeBooking(bookingDetails: BookingDetails): Resource<String>
    suspend fun getBookingDetail(bookingId: String): Resource<BookingDetails>
    suspend fun getBookingDetailsByEmail(email: String): Resource<List<BookingDetails>>
}