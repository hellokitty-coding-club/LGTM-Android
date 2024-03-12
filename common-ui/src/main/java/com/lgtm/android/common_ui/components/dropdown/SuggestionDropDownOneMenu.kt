package com.lgtm.android.common_ui.components.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lgtm.android.common_ui.theme.LGTMTheme

@Composable
fun SuggestionDropDownOneMenu(
    isExpanded: Boolean,
    text: String,
    onDismissRequest: () -> Unit,
    onMenuClick: () -> Unit
) {
    MaterialTheme(shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(10.dp))) {
        DropdownMenu(
            modifier = Modifier
                .background(
                    color = LGTMTheme.colors.white
                )
                .border(
                    width = 1.dp,
                    color = LGTMTheme.colors.gray_2,
                    shape = RoundedCornerShape(10.dp)
                ),
            expanded = isExpanded,
            onDismissRequest = onDismissRequest
        ) {
            DropdownMenuItem(
                onClick = {
                    onDismissRequest()
                    onMenuClick()
                }
            ) {
                Text(
                    text = text,
                    style = LGTMTheme.typography.body2,
                    color = LGTMTheme.colors.black
                )
            }
        }
    }
}