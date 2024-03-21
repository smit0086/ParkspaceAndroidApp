package com.parkspace.finder.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {
    private val _selectedStartDate = MutableStateFlow<Long?>(null)
    val selectedStartDate: StateFlow<Long?> = _selectedStartDate

    private val _selectedEndDate = MutableStateFlow<Long?>(null)
    val selectedEndDate: StateFlow<Long?> = _selectedEndDate

    // Other necessary ViewModel methods and state management
}