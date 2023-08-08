package com.example.composetemplate.network.ktor

import com.example.composetemplate.network.SsunNetworkDataSource
import com.example.composetemplate.network.model.NetworkPhoto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorSsunNetwork @Inject constructor() : SsunNetworkDataSource {

    private val client = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            gson()
        }
        defaultRequest {
            url("https://jsonplaceholder.typicode.com/")
        }
    }

    override suspend fun getPhotos(): List<NetworkPhoto> = client.get("photos").body()
}