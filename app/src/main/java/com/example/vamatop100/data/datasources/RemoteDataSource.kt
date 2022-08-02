package com.example.vamatop100.data.datasources

import com.example.vamatop100.core.ApiClient
import com.example.vamatop100.core.RetrofitHelper
import com.example.vamatop100.domain.entities.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "RemoteDataSource"

interface RemoteDataSource {
    suspend fun getAlbums(): List<Album>
}

class RemoteDataSourceImplementation : RemoteDataSource {

    private val retrofit = RetrofitHelper.getRetrofit()
    override suspend fun getAlbums(): List<Album> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getAlbums()
            response.feed.results
        }

    }
}