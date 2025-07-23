package com.example.loginx.welcome

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginx.R
import com.example.loginx.login.LoginActivity

@Composable
fun Welcome() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.valorantsc),
                contentDescription = "Valorant Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                contentScale = ContentScale.Crop
            )
        }

        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.black)
            )
        ) {
            Text(
                text = "Continue",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
    }
}


















