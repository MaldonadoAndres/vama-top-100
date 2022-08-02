package com.example.vamatop100.data.repositories

import android.util.Log
import com.example.vamatop100.data.datasources.LocalDataSource
import com.example.vamatop100.data.datasources.LocalDataSourceImplementation
import com.example.vamatop100.data.datasources.RemoteDataSource
import com.example.vamatop100.data.datasources.RemoteDataSourceImplementation
import com.example.vamatop100.domain.entities.Album
import com.example.vamatop100.domain.repositories.AlbumsRepository
import io.realm.kotlin.ext.toRealmList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "AlbumsRepositoryImpleme"

class AlbumsRepositoryImplementation : AlbumsRepository {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImplementation()
    private val localDataSource: LocalDataSource = LocalDataSourceImplementation()

    override suspend fun getAlbums(shouldRefresh: Boolean): List<Album> {
        return withContext(Dispatchers.IO) {
            try {
                var albums = localDataSource.retrieveAlbums()
                if (albums.isEmpty() || shouldRefresh) {
                    albums = remoteDataSource.getAlbums()
                    for (album in albums) {
                        album.realmGenres = album.genres.toRealmList()
                    }
                    localDataSource.saveAlbums(albums)
                }
                albums
            } catch (e: Exception) {
                Log.e(TAG, "getAlbums: ", e)
                emptyList()
            }

        }
    }
}