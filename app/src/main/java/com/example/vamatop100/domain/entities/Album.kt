package com.example.vamatop100.domain.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey


data class AlbumResponse(val feed: Feed)

data class Feed(
    @SerializedName("results") val results: List<Album> = listOf(),
    val copyright: String
)


class Album() : RealmObject, Parcelable {
    @SerializedName("id")
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
    var artistName: String = ""

    @SerializedName("artworkUrl100")
    var albumCover: String = ""
    var releaseDate: String = ""
    var url: String = ""

    @Ignore
    val genres: List<Genre> = listOf()
    var realmGenres: RealmList<Genre> = realmListOf()

    constructor(parcel: Parcel) : this() {
        _id = parcel.readString().toString()
        name = parcel.readString().toString()
        artistName = parcel.readString().toString()
        albumCover = parcel.readString().toString()
        releaseDate = parcel.readString().toString()
        url = parcel.readString().toString()
    }


    override fun toString(): String {
        return Gson().toJson(this)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(name)
        parcel.writeString(artistName)
        parcel.writeString(albumCover)
        parcel.writeString(releaseDate)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }


}

class Genre() : RealmObject, Parcelable {
    var name: String = ""

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Genre> {
        override fun createFromParcel(parcel: Parcel): Genre {
            return Genre(parcel)
        }

        override fun newArray(size: Int): Array<Genre?> {
            return arrayOfNulls(size)
        }
    }
}