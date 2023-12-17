package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import defaults.accent
import resources.save
import settings
import showSettingsWindow

@Composable
@Preview
fun SettingsWindow() {
    var hideSecret by remember { mutableStateOf(settings.value.hideSecret) }
    var hidePrivate by remember { mutableStateOf(settings.value.hidePrivate) }
    var hideRestricted by remember { mutableStateOf(settings.value.hideRestricted) }

    Column {
        Box(
            modifier = Modifier.padding(10.dp).fillMaxWidth()
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
        ) {
            Row {
                Checkbox(
                    hideSecret, onCheckedChange = {
                        hideSecret = !hideSecret
                    }, colors = CheckboxDefaults.colors(
                        checkedColor = accent,
                        uncheckedColor = Color.Gray,
                    )
                )

                Text(
                    "Hide entries tagged with Secret",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = TextUnit(1F, TextUnitType.Em)
                )
            }
        }

        Box(
            modifier = Modifier.padding(10.dp).fillMaxWidth()
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
        ) {
            Row {
                Checkbox(
                    hidePrivate, onCheckedChange = {
                        hidePrivate = !hidePrivate
                    }, colors = CheckboxDefaults.colors(
                        checkedColor = accent,
                        uncheckedColor = Color.Gray,
                    )
                )

                Text(
                    "Hide entries tagged with Private",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = TextUnit(1F, TextUnitType.Em)
                )
            }
        }

        Box(
            modifier = Modifier.padding(10.dp).fillMaxWidth()
                .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
        ) {
            Row {
                Checkbox(
                    hideRestricted, onCheckedChange = {
                        hideRestricted = !hideRestricted
                    }, colors = CheckboxDefaults.colors(
                        checkedColor = accent,
                        uncheckedColor = Color.Gray,
                    )
                )

                Text(
                    "Hide entries tagged with Restricted",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = TextUnit(1F, TextUnitType.Em)
                )
            }
        }

        Row {
            Button(
                onClick = {
                    settings.value.hideSecret = hideSecret
                    settings.value.hidePrivate = hidePrivate
                    settings.value.hideRestricted = hideRestricted

                    settings.value.save()
                    showSettingsWindow.value = false
                },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = accent)
            ) {
                Icon(Icons.Default.Check, "Save", tint = Color.White)
            }
        }
    }
}