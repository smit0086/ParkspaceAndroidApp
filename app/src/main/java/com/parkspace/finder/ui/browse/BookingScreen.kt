package com.parkspace.finder.ui.browse

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parkspace.finder.R
import com.parkspace.finder.data.BookingViewModel
import java.text.SimpleDateFormat
import androidx.compose.material.icons.filled.DateRange
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

@Composable
// fun BookingScreen(bookingViewModel: BookingViewModel) {
fun BookingScreen() {
    val context = LocalContext.current

    var startDate by remember { mutableStateOf<Long?>(null) }
    var endDate by remember { mutableStateOf<Long?>(null) }
    var startTime by remember { mutableStateOf<Date?>(null) }
    var endTime by remember { mutableStateOf<Date?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Calendar view to select start and end dates
        DateRangePicker(
            startDate = startDate,
            endDate = endDate,
            onStartDateSelected = { startDate = it },
            onEndDateSelected = { endDate = it },
            fragmentManager = getFragmentManager(context)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Time pickers for start and end times
        TimePicker(
            selectedTime = startTime,
            onTimeSelected = { startTime = it },
            label = "Start Time"
        )

        Spacer(modifier = Modifier.height(8.dp))

        TimePicker(
            selectedTime = endTime,
            onTimeSelected = { endTime = it },
            label = "End Time"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Handle booking */ }) {
            Text(text = "Book Parking Space")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show selected start and end dates and times
        startDate?.let {
            Text(text = "Start Date: ${convertLongToDateString(it)}")
        }
        endDate?.let {
            Text(text = "End Date: ${convertLongToDateString(it)}")
        }
        startTime?.let {
            Text(text = "Start Time: ${convertTimeToString(it)}")
        }
        endTime?.let {
            Text(text = "End Time: ${convertTimeToString(it)}")
        }
    }
}

@Composable
fun TimePicker(
    selectedTime: Date?,
    onTimeSelected: (Date) -> Unit,
    label: String
) {
    var currentTime by remember { mutableStateOf(Calendar.getInstance()) }
    val context = LocalContext.current

    Column {
        OutlinedTextField(
            value = selectedTime?.let { convertTimeToString(it) } ?: "",
            onValueChange = { /* Do nothing */ },
            label = { Text(text = label) },
            trailingIcon = { TimePickerIcon { showTimePickerDialog(context, currentTime) { time ->
                currentTime = time
                onTimeSelected(time.time)
            } } },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TimePickerIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.DateRange, // You can use the same icon for now
            contentDescription = stringResource(id = R.string.select_date) // Update the content description
        )
    }
}

private fun showTimePickerDialog(
    context: android.content.Context,
    currentTime: Calendar,
    onTimeSet: (Calendar) -> Unit
) {
    val hour = currentTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentTime.get(Calendar.MINUTE)

    val timePickerDialog = android.app.TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentTime.set(Calendar.MINUTE, minute)
            onTimeSet(currentTime)
        },
        hour,
        minute,
        false
    )
    timePickerDialog.show()
}

fun convertTimeToString(time: Date): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(time)
}

fun getFragmentManager(context: android.content.Context): FragmentManager {
    return when (context) {
        is androidx.fragment.app.FragmentActivity -> context.supportFragmentManager
        is androidx.fragment.app.Fragment -> context.childFragmentManager
        else -> throw IllegalArgumentException("Unsupported context type")
    }
}

@Composable
fun DateRangePicker(
    startDate: Long?,
    endDate: Long?,
    onStartDateSelected: (Long) -> Unit,
    onEndDateSelected: (Long) -> Unit,
    fragmentManager: FragmentManager // Pass the FragmentManager
) {
    OutlinedTextField(
        value = startDate?.let { convertLongToDateString(it) } ?: "",
        onValueChange = { /* Do nothing */ },
        label = { Text(text = "Start Date") },
        trailingIcon = { DatePickerIcon { showDatePicker(fragmentManager, onStartDateSelected) } },
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = endDate?.let { convertLongToDateString(it) } ?: "",
        onValueChange = { /* Do nothing */ },
        label = { Text(text = "End Date") },
        trailingIcon = { DatePickerIcon { showDatePicker(fragmentManager, onEndDateSelected) } },
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DatePickerIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            // Use an alternative icon available in Material3
            imageVector = Icons.Default.DateRange,
            contentDescription = stringResource(id = R.string.select_date)
        )
    }
}

fun showDatePicker(fragmentManager: FragmentManager, onDateSelected: (Long) -> Unit) {
    // Set up the MaterialDatePicker
    val builder = MaterialDatePicker.Builder.datePicker()
    val datePicker = builder.build()

    // Set listener for date selection
    datePicker.addOnPositiveButtonClickListener { timestamp ->
        onDateSelected(timestamp)
    }

    // Show the MaterialDatePicker
    datePicker.show(fragmentManager, "Date Picker")
}



@Composable
fun convertLongToDateString(time: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(time))
}

@Preview
@Composable
fun PreviewBookingScreen() {
//    BookingScreen(BookingViewModel())
    BookingScreen()
}
