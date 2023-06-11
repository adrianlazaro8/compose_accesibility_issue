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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.example.accesibilityissue.ui.theme.AccesibilityIssueTheme
import com.example.unmodifiablemodule.ContentTextUnmodifiable

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
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
                        modifier = Modifier.fillMaxSize().semantics(true) {
                            //We want to merge all the descendants and avoid the focus of accesibility services.
                            //However, by this way, we are losing useful semantic properties of Texts of ContentTextUnmodifiable,
                            //like testTag.
                            invisibleToUser()
                        }
                        ) {
                        ContentTextUnmodifiable(
                            title = "Title",
                            description = "Description"
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Checkbox(
                            checked = true, onCheckedChange = { },
                            modifier = Modifier.testTag("CHECKBOX")
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