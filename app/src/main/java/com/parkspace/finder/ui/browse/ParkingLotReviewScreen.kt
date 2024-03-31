package com.parkspace.finder.ui.browse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parkspace.finder.ui.theme.md_theme_light_primary

@Composable
fun ParkingLotReviewScreen(
    initialRating: Float = 4.5f,
    initialFeedback: String = "Great Experience!",
    onRatingSubmitted: (Float) -> Unit,
    onFeedbackSubmitted: (String) -> Unit
) {
    var rating by remember { mutableStateOf(initialRating) }
    var feedback by remember { mutableStateOf(initialFeedback) }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Review Parking Lot",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Rating Bar
                RatingBar(
                    rating = rating,
                    onRatingChanged = { newRating -> rating = newRating }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Feedback TextField
                OutlinedTextField(
                    value = feedback,
                    onValueChange = { newFeedback -> feedback = newFeedback },
                    label = { Text(
                        text = "Feedback",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ) },
                    maxLines = 5,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Submit Button
                Button(
                    onClick = {
                        // Callbacks to pass rating and feedback to the caller
                        onRatingSubmitted(rating)
                        onFeedbackSubmitted(feedback)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(md_theme_light_primary)
                ) {
                    Text(text = "Submit")
                }
            }
        }
    )
}

@Composable
fun RatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    // Custom rating bar implementation (you can replace it with a library or system widget)
    // Here, we are using a simple Row with Star icons for demonstration purposes
    Row {
        repeat(5) { index ->
            IconButton(
                onClick = { onRatingChanged(index + 1f) },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = if (index < rating.toInt()) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = null,
                    tint = Color.Blue
                )
            }
        }
    }
}
