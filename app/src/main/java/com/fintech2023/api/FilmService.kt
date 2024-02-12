package com.fintech2023.api

import com.fintech2023.data.FilmDetailResponse
import com.fintech2023.data.PopularFilmsResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {
    @GET("/api/v2.2/films/top")
    suspend fun topPhotos(
        @Query("page") page: Int,
        @Query("type") type: String = "TOP_100_POPULAR_FILMS"
    ): PopularFilmsResponse

    @GET("/api/v2.2/films/{id}")
    suspend fun filmDetails(
        @Path("id") id: String,
    ): FilmDetailResponse

    companion object {
        private const val BASE_URL = "https://kinopoiskapiunofficial.tech"

        fun create(): FilmService {
            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request().newBuilder()
                        .header("x-api-key", "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
                    chain.proceed(request.build())
                })
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmService::class.java)
        }
    }
}