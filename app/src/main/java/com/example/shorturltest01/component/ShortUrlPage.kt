package com.example.shorturltest01.component

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.shorturltest01.MainViewModel
import com.example.shorturltest01.TEST_URL
import kotlinx.coroutines.selects.select

//@Preview
@Composable
fun ShortenUrl(viewModel: MainViewModel, onClickEvent: () -> Unit) {

    val activity = LocalContext.current as? Activity
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { activity?.finish() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {


//                    value = viewModel.myUrl.value,
//                    onValueChange = { newString -> viewModel.myUrlChang(newString) },
                TextField(
                    value = viewModel.test.value,
                    onValueChange = { newString -> viewModel.myUrlChang(newString.text) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Ascii
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                val text = viewModel.test.value
                                viewModel.test.value = viewModel.test.value.copy(
                                    selection = TextRange(0, text.toString().length)
                                )
                            }
                        }
                )
                DisposableEffect(Unit) {
                    focusRequester.requestFocus()
                    onDispose { }
                }

                Spacer(modifier = Modifier.size(36.dp))
                Text(viewModel.shortUrl.value)
                Spacer(modifier = Modifier.size(16.dp))
                Text(viewModel.testState.value)
                Spacer(modifier = Modifier.size(72.dp))
                Button(
                    onClick = { onClickEvent() },
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text(text = "Test")
                }
            }
        }
    }
}