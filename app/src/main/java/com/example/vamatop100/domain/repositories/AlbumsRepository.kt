package com.example.vamatop100.domain.repositories

import com.example.vamatop100.domain.entities.Album

interface AlbumsRepository {
    suspend fun getAlbums(shouldRefresh: Boolean = false): List<Album>
}