package com.example.loginx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
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

suspend fun fetchPosts(): List<Agent> = withContext(Dispatchers.IO) {
    val url = URL("https://jsonplaceholder.typicode.com/posts")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.setRequestProperty("Content-Type", "application/json")

    val response = connection.inputStream.bufferedReader().readText()
    val jsonArray = JSONArray(response)

    val posts = mutableListOf<Agent>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val post = Agent(
            id = jsonObject.getInt("id"),
            title = jsonObject.getString("title"),
            body = jsonObject.getString("body"),
            userId = jsonObject.getInt("userId")
        )
        posts.add(post)
    }

    return@withContext posts
}