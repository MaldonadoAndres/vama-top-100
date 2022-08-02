package com.example.vamatop100.domain.usecases

import com.example.vamatop100.data.repositories.AlbumsRepositoryImplementation
import com.example.vamatop100.domain.entities.Album
import com.example.vamatop100.domain.repositories.AlbumsRepository

class GetAlbumsUseCase(private val repository: AlbumsRepository = AlbumsRepositoryImplementation()) {
    suspend operator fun invoke(shouldRefresh: Boolean = false): List<Album> =
        repository.getAlbums(shouldRefresh = shouldRefresh)
}