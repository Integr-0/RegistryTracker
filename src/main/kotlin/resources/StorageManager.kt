package resources

import java.nio.file.FileAlreadyExistsException
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries

val parent: String = "${System.getProperty("user.home")}/AppData/Roaming/RegistryTracker"

fun init(): List<Entry> {
    try {
        Files.createDirectories(Path("$parent/entries/saves"))
        println("Directory created: '${Path("$parent/entries/saves")}'")
    } catch (ex: FileAlreadyExistsException) {
        println("Directory already exists: '${Path("$parent/entries/saves")}'")
    }

    return loadAll()
}

fun Entry.delete() {
    Files.delete(
        Path(
            "$parent/entries/saves/${
                this.cDate.dayOfMonth.toString()
                        + "-" + this.cDate.monthValue.toString()
                        + "-" + this.cDate.year.toString()
                        + "-" + this.cTime.second.toString()
                        + "-" + this.cTime.minute.toString()
                        + "-" + this.cTime.hour.toString()
            }.json"
        )
    )
}

fun Entry.write(): Entry {
    val json = this.toJSON()
    Files.write(
        Path(
            "$parent/entries/saves/${
                this.cDate.dayOfMonth.toString()
                        + "-" + this.cDate.monthValue.toString()
                        + "-" + this.cDate.year.toString()
                        + "-" + this.cTime.second.toString()
                        + "-" + this.cTime.minute.toString()
                        + "-" + this.cTime.hour.toString()
            }.json"
        ),
        json.toByteArray()
    )
    return this
}

fun read(path: String): Entry {
    val json = Files.readAllLines(Path(path))
    val entry = Entry.fromJSON(json.joinToString())
    return entry
}

fun loadAll(): List<Entry> {
    val entries = Path("$parent/entries/saves").listDirectoryEntries()
    val allEntries: MutableList<Entry> = mutableListOf()

    for (e in entries) {
        allEntries += read(e.toString())
    }

    println("Loaded '${allEntries.count()}' entries")

    return allEntries
}