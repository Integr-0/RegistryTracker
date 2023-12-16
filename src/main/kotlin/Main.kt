import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import windows.defaultWindow
import windows.entryCreationWindow

var showCreationWindow = mutableStateOf(false)

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Registry Tracker") {
        defaultWindow()
    }

    if (showCreationWindow.value) {
        Window(onCloseRequest = {showCreationWindow.value = false}, title = "Registry Tracker", resizable = false) {
            entryCreationWindow()
        }
    }
}
