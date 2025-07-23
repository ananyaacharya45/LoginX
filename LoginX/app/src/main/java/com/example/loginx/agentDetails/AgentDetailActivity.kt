package com.example.loginx.agentDetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class AgentDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val agentUuid = intent.getStringExtra("AGENT_UUID")
        setContent {
            AgentList(agentUuid.toString())
        }
    }


}

suspend fun fetchAgent(agentUuid: String): AgentDetail = withContext(Dispatchers.IO) {
    val url = URL("https://valorant-api.com/v1/agents/" + agentUuid)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Content-Type", "application/json")

    val response = connection.inputStream.bufferedReader().readText()
    val jsonObject = JSONObject(response)
    val dataObject = jsonObject.getJSONObject("data")
    val agent = AgentDetail(
        uuid = dataObject.getString("uuid"),
        displayName = dataObject.getString("displayName"),
        description = dataObject.getString("description"),
        developerName = dataObject.getString("developerName"),
        displayIcon = dataObject.getString("displayIcon"),
        role = dataObject.getJSONObject("role").getString("displayName"),
    )

    return@withContext agent
}
