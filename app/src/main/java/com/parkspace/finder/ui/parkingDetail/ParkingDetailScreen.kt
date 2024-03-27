package com.parkspace.finder.ui.parkingDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.parkspace.finder.data.ParkingSpaceViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.parkspace.finder.R
import com.parkspace.finder.data.ParkingSpace
import com.parkspace.finder.data.ParkingSpaceRepository
import kotlinx.coroutines.launch
import com.parkspace.finder.ui.browse.Rating


//@Composable
//fun ParkingDetailScreen(navController: NavHostController) {
//    Text(text = "Parking Detail Screen")
//}

@Composable
fun Rating(rating: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..5) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = if (i <= rating) R.drawable.star_filled_24 else R.drawable.star_empty_24),
                contentDescription = "Rating Star",
                tint = if (i <= rating) Color(0xFFFAAF00) else Color(0xFFAAAAAA),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ParkingDetailScreen(
    navController: NavHostController,
    parkingSpaceName: String,
    viewModel: ParkingSpaceViewModel,
    parkingSpaceRepository: ParkingSpaceRepository
) {
    var parkingSpace by remember { mutableStateOf<ParkingSpace?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }
    val locationName = remember { mutableStateOf("Loading...") }

    LaunchedEffect(key1 = parkingSpaceName) {
        // Fetch parking space details when the parkingSpaceName changes
        coroutineScope.launch {
            try {
                val fetchedParkingSpace = parkingSpaceRepository.getParkingSpaceByName(parkingSpaceName)
                parkingSpace = fetchedParkingSpace
            } catch (e: Exception) {
                // Handle error
            }
//            try {
//                val fetchedParkingSpace = parkingSpaceRepository.getParkingSpaceByName(parkingSpaceName)
//                parkingSpace = fetchedParkingSpace
//                // Get the address based on latitude and longitude
//                fetchedParkingSpace?.let { space ->
//                    locationName.value = getLocationName(space.location.latitude, space.location.longitude)
//                }
//            } catch (e: Exception) {
//                // Handle error
//            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Parking Details",
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    parkingSpace?.let { space ->
                        IconButton(onClick = {
                            // Toggle favorite state
                            isFavorite = !isFavorite
                        }) {
                            val icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                            Icon(
                                imageVector = icon,
                                contentDescription = "Add to favorites"
                            )
                        }
                    }
                }
            )
        },
        content = {
            // Parking details content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                parkingSpace?.let { space ->
                    Text(
                        text = "${space.location}",
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = space.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Display hourly rate
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "$${space.hourlyPrice}/hr",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Display rating stars and rating value
                        Rating(rating = 4)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "23",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.location_24), // Your location icon resource
                                contentDescription = "Location",
                                tint = Color(0xFF777777),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "${String.format("%.2f", space.distanceFromCurrentLocation)} km away",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF777777),
                                modifier = Modifier.padding(start = 4.dp) // Add padding to separate the icon and text
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp)) // Add space between the first and second items
                        // Add the second item: "Available"
                        Text(
                            text = "Available",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF777777)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // Facilities section
                    Text(
                        text = "Facilities",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Display facilities in rounded rectangles
                    Row {
                        FacilityItem(name = "CCTV")
                        Spacer(modifier = Modifier.width(8.dp))
                        FacilityItem(name = "Hydraulic Parking")
                        Spacer(modifier = Modifier.width(8.dp))
                        FacilityItem(name = "Security")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        FacilityItem(name = "Automated Tickets")
                        Spacer(modifier = Modifier.width(8.dp))
                        FacilityItem(name = "Parking Assistance")
                    }
                    // Working Hours section
                    Text(
                        text = "Working Hours",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .background(color = Color.White, shape = MaterialTheme.shapes.medium)
                            .padding(16.dp)
                    ) {
                        // Display working hours
                        listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday").forEach { day ->
                            Row(
                                modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = day,
                                    color = Color.DarkGray, // Adjust color for days
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "8am - 8pm",
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        // Saturday and Sunday
                        Text(
                            text = "Saturday - Sunday",
                            color = Color.DarkGray, // Adjust color for days
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Text(
                            text = "Closed",
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    )
}
@Composable
fun FacilityItem(name: String) {
    Surface(
        color = Color.LightGray.copy(alpha = 0.4f),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold
        )
    }
}
