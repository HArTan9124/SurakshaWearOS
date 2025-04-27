package com.example.trial.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.trial.R

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            TrialTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    EmergencyImageButtons()
                }
            }
        }
    }

    private fun callEmergency(number: String) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                1
            )
            return
        }

        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$number")
        }

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to initiate call.", Toast.LENGTH_SHORT).show()
        }
    }

    @Composable
    fun EmergencyImageButtons() {
        // Creating a scrollable column
        val scrollState = rememberScrollState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState) // Make the column scrollable
        ) {
            // Add "Suraksha" TextView
            Text(
                text = "Suraksha",
                color = Color.White,
                fontSize = 32.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Row for Police and Ambulance images
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                EmergencyImage("police", "100")
                EmergencyImage("ambulance", "102")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Row for Fire and Safety images
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                EmergencyImage("fire", "101")
                EmergencyImage("safety", "112") // Safety number can vary depending on region
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }

    @Composable
    fun EmergencyImage(imageName: String, number: String) {
        val image: Painter = when (imageName) {
            "police" -> painterResource(id = R.drawable.police) // Replace with your image resource
            "ambulance" -> painterResource(id = R.drawable.ambulance) // Replace with your image resource
            "fire" -> painterResource(id = R.drawable.fire) // Replace with your image resource
            "safety" -> painterResource(id = R.drawable.women) // Replace with your image resource
            else -> painterResource(id = R.drawable.splash_icon) // Default image if needed
        }

        Box(
            modifier = Modifier
                .size(75.dp) // 75x75 dp size for each image
                .padding(8.dp)
                .background(Color.White, RoundedCornerShape(12.dp)) // White background
                .clickable {
                    callEmergency(number)
                }
        ) {
            Image(painter = image, contentDescription = imageName)
        }
    }
}

private fun MainActivity.TrialTheme(function: @Composable () -> Unit) {


}
