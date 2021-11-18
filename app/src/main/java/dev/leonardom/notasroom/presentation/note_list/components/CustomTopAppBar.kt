package dev.leonardom.notasroom.presentation.note_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.presentation.ui.noteAppColors

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    isLinearLayoutMode: Boolean,
    toggleLayoutMode: () -> Unit,
    toggleDarkMode: () -> Unit,
    onSearchQuery: (String) -> Unit
) {

    val layoutModeButton = if(isLinearLayoutMode) {
        painterResource(id = R.drawable.ic_grid)
    } else {
        painterResource(id = R.drawable.ic_list)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SearchBar(
            hint = "Search...",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.750f),
            onSearch = onSearchQuery
        )

        Image(
            modifier = Modifier
                .size(24.dp)
                .weight(0.125f)
                .align(Alignment.CenterVertically)
                .clickable { toggleLayoutMode() },
            painter = layoutModeButton,
            contentDescription = "LayoutModeButton",
            colorFilter = ColorFilter.tint(color = MaterialTheme.noteAppColors.onSecondaryColor)
        )

        Image(
            modifier = Modifier
                .size(24.dp)
                .weight(0.125f)
                .align(Alignment.CenterVertically)
                .clickable { toggleDarkMode() },
            painter = painterResource(id = R.drawable.ic_dark_mode),
            contentDescription = "DarkModeButton",
        )
    }

}

























