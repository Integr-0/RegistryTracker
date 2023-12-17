package resources

import net.harawata.appdirs.AppDirsFactory
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries

val parent: String = AppDirsFactory.getInstance().getUserDataDir("MainDir", "", "RegistryTracker")

fun init(): List<Entry> {
    Files.createDirectories(Path("$settingsParent/entries/saves"))
    println("Directory created: '${Path("$settingsParent/entries/saves")}'")

    return loadAll()
}

fun Entry.delete() {
    Files.delete(
        Path(
            "$settingsParent/entries/saves/${
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
            "$settingsParent/entries/saves/${
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
    val entries = Path("$settingsParent/entries/saves").listDirectoryEntries()
    val allEntries: MutableList<Entry> = mutableListOf()

    for (e in entries) {
        allEntries += read(e.toString())
    }

    println("Loaded '${allEntries.count()}' entries")

    return allEntries
}