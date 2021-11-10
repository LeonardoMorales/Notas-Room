package dev.leonardom.notasroom.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.text.TextStyle
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.presentation.ui.AppNotasRoomTheme
import dev.leonardom.notasroom.presentation.ui.RED50
import dev.leonardom.notasroom.presentation.ui.RED600
import dev.leonardom.notasroom.presentation.ui.noteAppColors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasRoomTheme {
                Text(
                    "Nuevo texto creado con Jetpack Compose",
                    style = TextStyle(
                        color = MaterialTheme.noteAppColors.blue009
                    )
                )
            }
        }
    }
}