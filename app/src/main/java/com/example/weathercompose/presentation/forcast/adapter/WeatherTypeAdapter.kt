package com.example.weathercompose.presentation.forcast.adapter

import com.example.weathercompose.domain.weather.WeatherType
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class WeatherTypeAdapter : JsonDeserializer<WeatherType>, JsonSerializer<WeatherType> {
    override fun serialize(src: WeatherType, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty("weatherDesc", src.weatherDesc)
        jsonObject.addProperty("iconRes", src.iconRes)
        return jsonObject
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): WeatherType {
        val jsonObject = json.asJsonObject
        val weatherDesc = jsonObject.get("weatherDesc").asString
        val iconRes = jsonObject.get("iconRes").asInt

        return WeatherType::class.sealedSubclasses
            .mapNotNull { it.objectInstance as? WeatherType }
            .firstOrNull { it.weatherDesc == weatherDesc && it.iconRes == iconRes }
            ?: throw JsonParseException("Unknown WeatherType: $weatherDesc, $iconRes")
    }
}