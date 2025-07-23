package com.example.loginx.login

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginx.agent.AgentActivity
import com.example.loginx.R

@Composable
fun Login() {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_curve1),
            contentDescription = "Login Image",
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentScale = ContentScale.FillBounds

        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .background(Color.White)
        ) {
            Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "Login to your account")
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = email, onValueChange = {
                email = it
            }, label = {
                Text(text = "Email Address")
            })
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = password, onValueChange = {
                password = it
            }, label = {
                Text(text = "Password")
            }, visualTransformation = PasswordVisualTransformation())
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val intent = Intent(context, AgentActivity::class.java)
                    Log.d("INFO","AgentActivitystarted")
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.peach_200)
                )
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            TextButton(onClick = {}) {
                Text(
                    text = "Forgot Password?",
                    color = colorResource(R.color.peach_200)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Or sign in with")
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.a), contentDescription = "Google",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                        })
                Image(
                    painter = painterResource(id = R.drawable.b), contentDescription = "Microsoft",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                        })
                Image(
                    painter = painterResource(id = R.drawable.c), contentDescription = "Facebook",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                        })
            }
        }
    }
}













