package resources

import com.google.gson.GsonBuilder
import java.time.LocalDate
import java.time.LocalTime

class Entry {
    var title = ""
    var content = ""
    var date = ""
    var access = ""

    var cTime: LocalTime = LocalTime.now()
    var cDate: LocalDate = LocalDate.now()

    fun setDate(day: String, month: String, year: String): Entry {
        this.date = "$day.$month.$year"
        return this
    }

    fun setTitle(title: String): Entry {
        this.title = title
        return this
    }

    fun setContent(content: String): Entry {
        this.content = content
        return this
    }

    fun setAccess(access: String): Entry {
        this.access = access
        return this
    }

    fun setCreatedVals(): Entry {
        cTime = LocalTime.now()
        cDate = LocalDate.now()
        return this
    }

    fun toJSON(): String {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        builder.registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
        return builder.create().toJson(this)
    }

    companion object {
        fun fromJSON(json: String): Entry {
            val builder = GsonBuilder()
            builder.registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            builder.registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
            val parsed = builder.create().fromJson(json, Entry::class.java)
            return parsed
        }
    }
}