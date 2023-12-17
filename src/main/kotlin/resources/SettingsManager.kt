package resources

import net.harawata.appdirs.AppDirsFactory
import settings
import java.nio.file.Files
import kotlin.io.path.Path

val settingsParent: String = AppDirsFactory.getInstance().getUserDataDir("MainDir", "", "RegistryTracker")

fun initSettings(): Settings {
    Files.createDirectories(Path("$settingsParent/settings"))
    println("Directory created: '${Path("$settingsParent/settings")}'")

    if (!Files.exists(Path("$settingsParent/settings/settings.json"))) settings.value.save()
    return loadSettings()
}

fun Settings.save() {
    val json = this.toJSON()
    Files.write(
        Path("$settingsParent/settings/settings.json"),
        json.toByteArray()
    )
}

fun loadSettings(): Settings {
    val json = Files.readAllLines(Path("$settingsParent/settings/settings.json"))
    val settings = Settings.fromJSON(json.joinToString())
    return settings
}