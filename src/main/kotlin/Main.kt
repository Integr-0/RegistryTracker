
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import resources.Entry
import resources.init
import windows.DefaultWindow
import windows.EntryCreationWindow

var showCreationWindow = mutableStateOf(false)
var entries: MutableList<Entry> = mutableListOf()
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
    }
}
