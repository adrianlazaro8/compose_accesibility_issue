package com.example.unmodifiablemodule

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

@Composable
fun WrappedText(
    testTag: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.semantics {
            this.testTag = testTag
            //Other relevant semantic properties
        }
    )
}
