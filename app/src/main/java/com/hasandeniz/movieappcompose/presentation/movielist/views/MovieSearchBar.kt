package com.hasandeniz.movieappcompose.presentation.movielist.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MovieSearchBar(modifier: Modifier, hint: String = "", onSearch: (String) -> Unit = {}) {
    var text by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text.trim())
                focusManager.clearFocus()
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(Color.Black),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSearch(text.trim())
                        focusManager.clearFocus()
                    }
                ) {
                    Icon(Icons.Default.Search, "Search Icon", tint = Color.Gray)
                }
            },
            placeholder = { Text(text = hint, color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

    }
}