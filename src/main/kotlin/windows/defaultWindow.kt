package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import defaults.colors
import showCreationWindow

@Composable
@Preview
fun defaultWindow() {
    MaterialTheme(colors = colors) {
        Row {
            Button(
                onClick = {
                    showCreationWindow.value = true
                },
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Add, "New Entry", tint = Color.White)
            }

            Button(onClick = {

            }, modifier = Modifier.padding(10.dp), shape = RoundedCornerShape(10.dp)) {
                Icon(Icons.Default.Settings, "New Entry", tint = Color.White)
            }
        }
    }
}