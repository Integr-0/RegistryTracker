package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import defaults.colors
import entries
import resources.Entry
import resources.write
import showCreationWindow
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun EntryCreationWindow() {
    var title by remember { mutableStateOf("") }

    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    var content by remember { mutableStateOf("") }

    val numPattern = remember { Regex("^\\d+\$") }

    var access by remember { mutableStateOf("Public") }

    MaterialTheme(colors = colors) {
        Column {
            Row {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Title") },
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color(76, 158, 152),
                        focusedIndicatorColor = Color(76, 158, 152)
                    )
                )
            }

            Row {
                OutlinedTextField(
                    value = day,
                    onValueChange = { if (it.isEmpty() || it.matches(numPattern)) day = it },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Day") },
                    modifier = Modifier.padding(10.dp),
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Default.DateRange,
                            "Set Today",
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Default)
                                .clickable { day = LocalDate.now().dayOfMonth.toString() })
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color(76, 158, 152),
                        focusedIndicatorColor = Color(76, 158, 152)
                    )
                )

                OutlinedTextField(
                    value = month,
                    onValueChange = { if (it.isEmpty() || it.matches(numPattern)) month = it },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Month") },
                    modifier = Modifier.padding(10.dp),
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Default.DateRange,
                            "Set Today",
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Default)
                                .clickable { month = LocalDate.now().monthValue.toString() })
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color(76, 158, 152),
                        focusedIndicatorColor = Color(76, 158, 152)
                    )
                )

                OutlinedTextField(
                    value = year,
                    onValueChange = { if (it.isEmpty() || it.matches(numPattern)) year = it },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Year") },
                    modifier = Modifier.padding(10.dp),
                    singleLine = true,
                    trailingIcon = {
                        Icon(Icons.Default.DateRange,
                            "Set Today",
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Default)
                                .clickable { year = LocalDate.now().year.toString() })
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color(76, 158, 152),
                        focusedIndicatorColor = Color(76, 158, 152)
                    )
                )
            }

            Row {
                FilterChip(
                    access == "Public",
                    onClick = { access = "Public" },
                    content = { Text("Public") },
                    modifier = Modifier.padding(10.dp),
                    border = BorderStroke(1.dp, color = if (access == "Public") Color(76, 158, 152) else Color.Gray),
                    selectedIcon = { Icon(Icons.Default.CheckCircle, "Selected", tint = Color(76, 158, 152)) },
                    colors = ChipDefaults.filterChipColors(selectedContentColor = Color(76, 158, 152))
                )
                FilterChip(
                    access == "Restricted",
                    onClick = { access = "Restricted" },
                    content = { Text("Restricted") },
                    modifier = Modifier.padding(10.dp),
                    border = BorderStroke(
                        1.dp,
                        color = if (access == "Restricted") Color(76, 158, 152) else Color.Gray
                    ),
                    selectedIcon = { Icon(Icons.Default.CheckCircle, "Selected", tint = Color(76, 158, 152)) },
                    colors = ChipDefaults.filterChipColors(selectedContentColor = Color(76, 158, 152))
                )
                FilterChip(
                    access == "Private",
                    onClick = { access = "Private" },
                    content = { Text("Private") },
                    modifier = Modifier.padding(10.dp),
                    border = BorderStroke(1.dp, color = if (access == "Private") Color(76, 158, 152) else Color.Gray),
                    selectedIcon = { Icon(Icons.Default.CheckCircle, "Selected", tint = Color(76, 158, 152)) },
                    colors = ChipDefaults.filterChipColors(selectedContentColor = Color(76, 158, 152))
                )
                FilterChip(
                    access == "Secret",
                    onClick = { access = "Secret" },
                    content = { Text("Secret") },
                    modifier = Modifier.padding(10.dp),
                    border = BorderStroke(1.dp, color = if (access == "Secret") Color(76, 158, 152) else Color.Gray),
                    selectedIcon = { Icon(Icons.Default.CheckCircle, "Selected", tint = Color(76, 158, 152)) },
                    colors = ChipDefaults.filterChipColors(selectedContentColor = Color(76, 158, 152))
                )
            }

            Row {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    shape = RoundedCornerShape(10.dp),
                    label = { Text("Content") },
                    modifier = Modifier.padding(10.dp).fillMaxWidth().height(240.dp),
                    singleLine = false,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = Color(76, 158, 152),
                        focusedIndicatorColor = Color(76, 158, 152)
                    )
                )
            }

            Row {
                Button(
                    onClick = {
                        val entry = Entry()
                            .setTitle(title)
                            .setAccess(access)
                            .setContent(content)
                            .setDate(day, month, year)
                            .write()
                        entries += entry
                        showCreationWindow.value = false
                    },
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(76, 158, 152))
                ) {
                    Icon(Icons.Default.Add, "Add", tint = Color.White)
                }
            }
        }
    }
}