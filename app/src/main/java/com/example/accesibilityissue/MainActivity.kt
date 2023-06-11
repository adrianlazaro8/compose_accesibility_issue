package com.example.accesibilityissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import com.example.accesibilityissue.ui.theme.AccesibilityIssueTheme
import com.example.unmodifiablemodule.ContentTextUnmodifiable

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccesibilityIssueTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                        ) {
                        ContentTextUnmodifiable(
                            title = "Title",
                            description = "Description",
                            //We want to avoid the focus of accessibility services for child elements.
                            //However, by this way, we are losing useful semantic properties of Texts of ContentTextUnmodifiable,
                            //like testTag, and we shouldn't set that property here because potentially "ContentTextUnmodifiable"
                            //can be used from other Composables.
                            //Usually, method invisibleToUser() can be invoked for avoid focus too, but
                            //in the same way we're losing useful children semantic properties like testTag.
                            //Modifier's "focusable" is not working either for complex views.
                            modifier = Modifier.clearAndSetSemantics { }
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Checkbox(
                            //useless checkbox
                            checked = true, onCheckedChange = { },
                            modifier = Modifier.testTag("checkbox")
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AccesibilityIssueTheme {
        Greeting("Android")
    }
}