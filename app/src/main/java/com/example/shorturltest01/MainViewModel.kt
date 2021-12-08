package com.example.shorturltest01

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

const val BASE_URL = "http://tinyurl.com/api-create.php?url="
const val TEST_URL = "https://www.example.com/"

class MainViewModel : ViewModel() {
    var myUrl = mutableStateOf("https://www.example.com/")

    fun myUrlChang(str: String) {
        this.myUrl.value = str
    }

    var shortUrl = mutableStateOf("")

    var testState = mutableStateOf("")

    var test = mutableStateOf(TextFieldValue(myUrl.value))
}