package com.example.vamatop100.data.datasources

import com.example.vamatop100.domain.entities.Album
import com.example.vamatop100.domain.entities.Genre
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

interface LocalDataSource {
    suspend fun saveAlbums(albums: List<Album>)
    suspend fun retrieveAlbums(): List<Album>
}

class LocalDataSourceImplementation : LocalDataSource {
    var realm: Realm

    init {
        val config = RealmConfiguration.Builder(schema = setOf(Album::class, Genre::class)).build()
        realm = Realm.open(config)
    }

    override suspend fun saveAlbums(albums: List<Album>) {
        realm.write {
            query<Album>().find().also { delete(it) }
        }
        for (album in albums) {
            realm.write {
                copyToRealm(album)
            }
        }
    }

    override suspend fun retrieveAlbums(): List<Album> {
        return realm.query<Album>().find().toList()
    }

}