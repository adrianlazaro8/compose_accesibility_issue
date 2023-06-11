package com.example.accesibilityissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.accesibilityissue.ui.theme.AccesibilityIssueTheme
import com.example.unmodifiablemodule.WrappedText

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
                    //We want to avoid the focus of accessibility services for text elements, only want focus on checkbox.
                    //For achieve that, we're using invisibleToUser semantic property inside of each WrappedText.
                    //The tradeoff of this, is that we are losing some useful semantic properties like testTag
                    //that is being set in the module where we don't have access, the "UnmodifiableModule".
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column {
                            WrappedText(
                                testTag = "SecondText",
                                text = "Sample",
                                modifier = Modifier.semantics {
                                    invisibleToUser()
                                }
                            )
                            WrappedText(
                                testTag = "SecondText",
                                text = "Sample second line",
                                modifier = Modifier.semantics {
                                    invisibleToUser()
                                }
                            )
                        }

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