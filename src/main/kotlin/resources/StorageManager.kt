package resources

import java.nio.file.FileAlreadyExistsException
import java.nio.file.Files
import java.time.LocalDate
import java.time.LocalTime
import kotlin.io.path.Path
import kotlin.io.path.listDirectoryEntries

fun init(): List<Entry> {
    try {
        Files.createDirectory(Path("./entries/saves"))
        println("Directory created: '${Path("./entries/saves")}'")
    } catch (ex: FileAlreadyExistsException) {
        println("Directory already exists: '${Path("./entries/saves")}'")
    }

    return loadAll()
}

fun Entry.write(): Entry {
    val json = this.toJSON()
    Files.write(
        Path(
            "./entries/saves/${
                LocalDate.now().dayOfMonth.toString()
                        + "-" + LocalDate.now().monthValue.toString()
                        + "-" + LocalDate.now().year.toString()
                        + "-" + LocalTime.now().second.toString()
                        + "-" + LocalTime.now().minute.toString()
                        + "-" + LocalTime.now().hour.toString()
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
    val entries = Path("./entries/saves").listDirectoryEntries()
    val allEntries: MutableList<Entry> = mutableListOf()

    for (e in entries) {
        allEntries += read(e.toString())
    }

    println("Loaded '${allEntries.count()}' entries")

    return allEntries
}