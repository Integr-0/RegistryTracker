package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import currentEditedEntry
import defaults.accent
import defaults.colors
import entries
import settings
import showCreationWindow
import showEditorWindow
import showSettingsWindow

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun DefaultWindow() {
    MaterialTheme(colors = colors) {
        Column {
            Row {
                Button(
                    onClick = {
                        showCreationWindow.value = true
                    },
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = accent)
                ) {
                    Icon(Icons.Default.Add, "New Entry", tint = Color.White)
                }

                Button(onClick = {
                    showSettingsWindow.value = true
                }, modifier = Modifier.padding(10.dp), shape = RoundedCornerShape(10.dp)) {
                    Icon(Icons.Default.Settings, "Settings", tint = Color.White)
                }
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                for (e in entries) {
                    if (e.access != "Secret" && settings.value.hideSecret || !settings.value.hideSecret) {
                        if (e.access != "Private" && settings.value.hidePrivate || !settings.value.hidePrivate) {
                            if (e.access != "Restricted" && settings.value.hideRestricted || !settings.value.hideRestricted) {
                                Row {
                                    Button(
                                        onClick = {
                                            currentEditedEntry.value = e
                                            showEditorWindow.value = true;
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                                            .height(40.dp),
                                        shape = RoundedCornerShape(10.dp),
                                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                        border = BorderStroke(1.dp, color = Color.Gray),
                                    ) {
                                        Box(modifier = Modifier.fillMaxWidth()) {
                                            Row(
                                                horizontalArrangement = Arrangement.Start,
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Text(e.title)
                                            }
                                            Row(
                                                horizontalArrangement = Arrangement.End,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                FilterChip(
                                                    true,
                                                    onClick = {},
                                                    content = { Text(e.date) },
                                                    border = BorderStroke(1.dp, color = Color.Gray),
                                                    modifier = Modifier.padding(end = 10.dp)
                                                )

                                                FilterChip(
                                                    true,
                                                    onClick = {},
                                                    content = { Text(e.access) },
                                                    border = BorderStroke(1.dp, color = Color.Gray),
                                                    modifier = Modifier.padding(start = 10.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}