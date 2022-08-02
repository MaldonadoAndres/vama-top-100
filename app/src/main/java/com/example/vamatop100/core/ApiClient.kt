package com.example.vamatop100.core

import com.example.vamatop100.domain.entities.AlbumResponse
import retrofit2.http.GET

interface ApiClient {
    @GET("api/v2/us/music/most-played/100/albums.json")
    suspend fun getAlbums(): AlbumResponse
}