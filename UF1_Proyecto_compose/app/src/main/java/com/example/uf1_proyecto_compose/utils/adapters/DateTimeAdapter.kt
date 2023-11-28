package com.example.uf1_proyecto_compose.utils.adapters

import com.example.uf1_proyecto_compose.utils.formatter.FormatterConstraint
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeTypeAdapter : TypeAdapter<LocalTime>() {

    private val formatter: DateTimeFormatter = FormatterConstraint.dateFormat

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: LocalTime?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(formatter.format(value))
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): LocalTime? {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return null
        }
        val timeString = `in`.nextString()
        return try {
            LocalTime.parse(timeString, formatter)
        } catch (e: Exception) {
            null
        }
    }
}