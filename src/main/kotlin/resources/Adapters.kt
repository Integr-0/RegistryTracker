package resources

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDate
import java.time.LocalTime


class LocalDateAdapter : TypeAdapter<LocalDate?>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): LocalDate? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val xy = reader.nextString()
        val parts = xy.split("-")
        val dayOfMonth = parts[0].toInt()
        val monthValue = parts[1].toInt()
        val year = parts[2].toInt()
        return LocalDate.of(year, monthValue, dayOfMonth)
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: LocalDate?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        val xy: String = value.dayOfMonth.toString() + "-" + value.monthValue.toString() + "-" + value.year.toString()
        writer.value(xy)
    }
}

class LocalTimeAdapter : TypeAdapter<LocalTime?>() {
    @Throws(IOException::class)
    override fun read(reader: JsonReader): LocalTime? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val xy = reader.nextString()
        val parts = xy.split("-")
        val second = parts[0].toInt()
        val minute = parts[1].toInt()
        val hour = parts[2].toInt()
        return LocalTime.of(hour, minute, second)
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, value: LocalTime?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        val xy: String = value.second.toString() + "-" + value.minute.toString() + "-" + value.hour.toString()
        writer.value(xy)
    }
}