package com.parkspace.finder.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.parkspace.finder.R
import com.parkspace.finder.data.AuthViewModel
import com.parkspace.finder.navigation.ROUTE_LOGIN
import com.parkspace.finder.ui.theme.spacing

// Sample data class
data class ParkingDetails(
    val name: String,
    val address: String,
    val availability: Int,
    val price: Double
)

@Composable
fun ParkingDetails(viewModel: AuthViewModel?, navController: NavHostController) {
    val spacing = MaterialTheme.spacing
    val parkingDetails = ParkingDetails(
        name = "parkingLotName", // Assuming we use the name for now
        address = "123 Park Avenue, Anytown",
        availability = 10,
        price = 3.50
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.medium)
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_image), // Placeholder image
            contentDescription = null, // Add alt text for accessibility
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = parkingDetails.name,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = parkingDetails.address,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Availability: ${parkingDetails.availability} spots",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = spacing.small)
            )
            Text(
                text = "Price: $${String.format("%.2f", parkingDetails.price)}/hour",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = spacing.small)
            )

            Spacer(modifier = Modifier.weight(1f)) // Pushes the button to the bottom

            Button(
                onClick = { /* Handle booking logic here */ },
                modifier = Modifier
                    .align(Alignment.End) // Align button to the right
            ) {
                Text("Book")
            }
        }
    }
}
