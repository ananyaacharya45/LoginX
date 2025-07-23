package com.example.loginx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class AgentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgentList()
        }
    }


}

suspend fun fetchAgents(): List<Agent> = withContext(Dispatchers.IO) {
    val url = URL("https://valorant-api.com/v1/agents")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Content-Type", "application/json")

    val response = connection.inputStream.bufferedReader().readText()
    val jsonObject = JSONObject(response)
    val dataArray = jsonObject.getJSONArray("data")
    val agents = mutableListOf<Agent>()
    for (i in 0 until dataArray.length()) {
        val jsonObject = dataArray.getJSONObject(i)
        val agent = Agent(
            uuid = jsonObject.getString("uuid"),
            displayName = jsonObject.getString("displayName"),
            description = jsonObject.getString("description"),
            developerName = jsonObject.getString("developerName")
        )
        agents.add(agent)
    }

    return@withContext agents
}