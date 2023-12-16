package resources

import com.google.gson.GsonBuilder

class Entry {
    var title = ""
    var content = ""
    var date = ""
    var access = ""
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

    fun toJSON(): String = GsonBuilder().create().toJson(this)

    companion object {
        fun fromJSON(json: String): Entry {
            val builder = GsonBuilder().create()
            val parsed = builder.fromJson(json, Entry::class.java)
            return parsed
        }
    }
}