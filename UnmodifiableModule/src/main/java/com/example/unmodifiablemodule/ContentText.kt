package com.example.unmodifiablemodule

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

@Composable
fun ContentTextUnmodifiable(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            modifier = Modifier.semantics {
                testTag = "Text1"
                //Other relevant semantic properties
            }
        )
        Text(
            text = description,
            modifier = Modifier.semantics {
                testTag = "Text2"
                //Other relevant semantic properties
            }
        )
    }
}
