package com.example.unit3project3_animalapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit3project3_animalapps.ui.theme.Unit3Project3AnimalAppsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unit3Project3AnimalAppsTheme {
                ZebberScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // ✅ Local opt-in (removes warning safely)
@Composable
fun ZebberScreen() {
    var showDetails by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "🦓 Zebber",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🦓 Logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE5E7EB)),
                contentAlignment = Alignment.Center
            ) {
                Text("🦓", fontSize = 56.sp)
            }

            // Title
            Text(
                text = "Ride with stripes.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            // Description
            Text(
                text = "Zebber — the bold new way to get around.\n" +
                        "Why blend in when you can arrive in style?",
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            // Button
            Button(
                onClick = { showDetails = !showDetails },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF111827))
            ) {
                Text(
                    text = if (showDetails) "Hide Details" else "Book a Ride",
                    color = Color.White
                )
            }

            // Info box
            if (showDetails) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    color = Color(0xFFF1F5F9),
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🚗 Quick pickup in under 5 minutes")
                        Text("🎯 Choose your destination — we’ll find your zebra")
                        Text("💳 Pay easily and earn ‘Stripe Points’")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Nearby Zebbers",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            // Map
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFDDE3EA)),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val stroke = Stroke(width = 6f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f)))
                    drawLine(
                        color = Color(0xFF9CA3AF),
                        start = androidx.compose.ui.geometry.Offset(100f, 50f),
                        end = androidx.compose.ui.geometry.Offset(size.width - 100f, size.height - 50f),
                        strokeWidth = 6f,
                        pathEffect = stroke.pathEffect
                    )

                    // Pickup circle
                    drawCircle(
                        color = Color(0xFF22C55E),
                        radius = 16f,
                        center = androidx.compose.ui.geometry.Offset(100f, 50f)
                    )

                    // Dropoff circle
                    drawCircle(
                        color = Color(0xFFEF4444),
                        radius = 16f,
                        center = androidx.compose.ui.geometry.Offset(size.width - 100f, size.height - 50f)
                    )
                }

                Text(
                    "🗺️ Map",
                    color = Color(0xFF111827),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ZebberPreview() {
    Unit3Project3AnimalAppsTheme {
        ZebberScreen()
    }
}
