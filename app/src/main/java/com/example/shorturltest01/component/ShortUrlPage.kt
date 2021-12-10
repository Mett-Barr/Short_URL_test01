package com.example.shorturltest01.component

import android.app.Activity
import android.util.Log
import android.view.WindowInsets
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowInsetsControllerCompat
import com.example.shorturltest01.MainViewModel

//@Preview
@Composable
fun ShortenUrl(
    viewModel: MainViewModel,
    urlChange: (String) -> Unit,
    onClickEvent: () -> Unit
) {

    val activity = LocalContext.current as? Activity
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val testState = remember {
        mutableStateOf(TextFieldValue(viewModel.testUrl.value.text))
    }

    val testViewModelState by viewModel.testUrl


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .clickable(
//                interactionSource = MutableInteractionSource(),
//                indication = null
//            ) { activity?.finish() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {

    Dialog(onDismissRequest = { activity?.finish() }) {
        Card(
            elevation = 8.dp,
            modifier = Modifier
//                .padding(48.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    focusManager.clearFocus()
                }
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

                OutlinedTextField(
                    value = viewModel.testUrl.value,
                    onValueChange = { viewModel.testUrl.value = it },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Ascii
                    ),
                    modifier = Modifier
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                val text = viewModel.testUrl.value.text
                                viewModel.testUrl.value = viewModel.testUrl.value.copy(
                                    selection = TextRange(0, text.length)
                                )
                            }
                        }
                        .then(
                            (when {
                                !viewModel.isNetworkUrl() -> {
                                    Modifier.focusRequester(focusRequester)
                                }
                                else -> Modifier
                            })
                        )
                )
                if (!viewModel.isNetworkUrl()) {
                    DisposableEffect(Unit) {
                        focusRequester.requestFocus()
                        onDispose { }
                    }
                }


//                TextField(
//                    value = viewModel.myUrl.value,
//                    onValueChange = { textFieldValue -> viewModel.myUrlChange(textFieldValue) },
//                    keyboardOptions = KeyboardOptions(
//                        imeAction = ImeAction.Done,
//                        keyboardType = KeyboardType.Ascii
//                    ),
//                    modifier = Modifier
//                        .focusRequester(focusRequester)
//                        .onFocusChanged { focusState ->
//                            if (focusState.isFocused) {
//                                val text = viewModel.testUrl.value
//                                viewModel.testUrl.value = viewModel.testUrl.value.copy(
//                                    selection = TextRange(0, text.toString().length)
//                                )
//                            }
//                        }
//                )

                Spacer(modifier = Modifier.size(36.dp))
                Text(viewModel.shortUrl.value)

//                Spacer(modifier = Modifier.size(16.dp))

//                    DisposableEffect(Unit) {
//                        focusRequester.requestFocus()
//                        onDispose { }
//                    }

                Spacer(modifier = Modifier.size(16.dp))
                OutlinedTextField(
                    value = testState.value,
                    onValueChange = { text -> testState.value = text },
                    modifier = Modifier
//                            .focusRequester(focusRequester = focusRequester)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                val text = testState.value.text
                                testState.value = testState.value.copy(
                                    selection = TextRange(0, text.length)
                                )
                                Log.d("!!!", "ShortenUrl: ${text.length}")
                            }
                        }
                )


                Spacer(modifier = Modifier.size(16.dp))
                Text(viewModel.networkState.value)

                Spacer(modifier = Modifier.size(72.dp))
                Button(
                    onClick = { onClickEvent() },
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text(text = "Test")
                }
                androidx.compose.material3.Button(onClick = { /*TODO*/ }) {
                    Text(text = "Test")
                }

            }
        }
    }
}
