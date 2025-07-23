@file:JvmName("AgentDetailKt")

package com.example.loginx.agentDetails

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun AgentList(agentUuid: String) {
    val initAgent = AgentDetail(
        "1", "John Doe", "test", "",
        displayIcon = "",
        role = ""
    )
    var agent by remember {
        mutableStateOf<AgentDetail>(
            initAgent
        )
    }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            val fetchedAgent = fetchAgent(agentUuid)
            agent = fetchedAgent
            isLoading = false
        } catch (e: Exception) {
            errorMessage = e.message
            isLoading = false
            Toast.makeText(context, "Error fetching data: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agents from API",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Loading agents...")
                    }
                }
            }

            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: $errorMessage",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            else -> {
                Box(

                ) {

                    AgentItem(agent = agent)

                }
            }
        }
    }

}

@Composable
fun AgentItem(agent: AgentDetail) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = agent.displayName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF0000),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = agent.description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "User ID: ${agent.uuid}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Light
            )
        }
    }
}











