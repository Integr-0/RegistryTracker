package resources

import com.google.gson.GsonBuilder

class Settings {
    var hideSecret = false
    var hidePrivate = false
    var hideRestricted = false

    fun toJSON(): String {
        val builder = GsonBuilder()
        return builder.create().toJson(this)
    }

    companion object {
        fun fromJSON(json: String): Settings {
            val builder = GsonBuilder()
            val parsed = builder.create().fromJson(json, Settings::class.java)
            return parsed
        }
    }
}