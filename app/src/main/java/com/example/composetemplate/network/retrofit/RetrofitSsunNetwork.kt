package com.example.composetemplate.network.retrofit

import com.example.composetemplate.network.SsunNetworkDataSource
import com.example.composetemplate.network.model.NetworkPhoto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitSsunNetworkApi {

    @GET("photos")
    suspend fun getPhotos(): List<NetworkPhoto>
}

@Singleton
class RetrofitSsunNetwork @Inject constructor() : SsunNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitSsunNetworkApi::class.java)

    override suspend fun getPhotos(): List<NetworkPhoto> = networkApi.getPhotos()
}