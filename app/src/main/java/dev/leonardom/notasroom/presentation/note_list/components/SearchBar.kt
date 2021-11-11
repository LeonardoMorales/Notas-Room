package dev.leonardom.notasroom.presentation.note_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dev.leonardom.notasroom.presentation.ui.noteAppColors

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {  }
) {

    var text by remember { mutableStateOf("") }

    var isHintDisplayed by remember{ mutableStateOf(hint != "") }

    val focusManager = LocalFocusManager.current

    Box(modifier = modifier){

        BasicTextField(
            value = text,
            onValueChange = { newSearch ->
                text = newSearch
                onSearch(newSearch)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.noteAppColors.onSecondaryColor
            ),
            cursorBrush = SolidColor(MaterialTheme.noteAppColors.onSecondaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.noteAppColors.secondaryColor, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isEmpty()
                },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                }
            ),
        )

        if(isHintDisplayed) {
            Text(
                text = hint,
                color = MaterialTheme.noteAppColors.onSecondaryColor,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }

}
















