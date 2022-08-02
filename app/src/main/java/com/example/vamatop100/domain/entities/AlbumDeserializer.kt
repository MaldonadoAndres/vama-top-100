package com.example.vamatop100.domain.entities

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class AlbumDeserializer : JsonDeserializer<Album> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Album {
        val results = json?.asJsonObject?.getAsJsonObject("feed")?.getAsJsonArray("results")
        return Gson().fromJson(results, Album::class.java)
    }
}