
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import resources.Entry
import resources.init
import windows.DefaultWindow
import windows.EntryCreationWindow
import windows.EntryEditorWindow

var showCreationWindow = mutableStateOf(false)
var showEditorWindow = mutableStateOf(false)
var entries: MutableList<Entry> = mutableListOf()

var currentEditedEntry: MutableState<Entry?> = mutableStateOf(null)
fun main() {
    entries = init().toMutableList()

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
    }
}
