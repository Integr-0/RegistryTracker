
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import resources.Entry
import resources.Settings
import resources.init
import resources.initSettings
import windows.DefaultWindow
import windows.EntryCreationWindow
import windows.EntryEditorWindow
import windows.SettingsWindow

var showCreationWindow = mutableStateOf(false)
var showEditorWindow = mutableStateOf(false)
var showSettingsWindow = mutableStateOf(false)

var entries: MutableList<Entry> = mutableListOf()
var settings = mutableStateOf(Settings())

var currentEditedEntry: MutableState<Entry?> = mutableStateOf(null)
fun main() {
    entries = init().toMutableList()
    settings.value = initSettings()

    application {
        Window(onCloseRequest = ::exitApplication, title = "Registry Tracker", icon = painterResource("Tracker.svg")) {
            DefaultWindow()
        }

        if (showCreationWindow.value) {
            Window(onCloseRequest = { showCreationWindow.value = false }, title = "New Entry", resizable = false, icon = painterResource("Tracker.svg")) {
                EntryCreationWindow()
            }
        }

        if (showEditorWindow.value) {
            Window(onCloseRequest = { showEditorWindow.value = false }, title = "Editing Entry", resizable = false, icon = painterResource("Tracker.svg")) {
                EntryEditorWindow(currentEditedEntry.value!!)
            }
        }

        if (showSettingsWindow.value) {
            Window(onCloseRequest = { showSettingsWindow.value = false }, title = "Settings", resizable = false, icon = painterResource("Tracker.svg")) {
                SettingsWindow()
            }
        }
    }
}
