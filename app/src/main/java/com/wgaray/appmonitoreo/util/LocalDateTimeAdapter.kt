package com.wgaray.appmonitoreo.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")

    override fun write(out: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            out.nullValue()
            return
        }
        val str = value.format(formatter)
        out.value(str)
    }

    override fun read(reader: JsonReader): LocalDateTime? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val str = reader.nextString()
        return LocalDateTime.parse(str, formatter)
    }
}
