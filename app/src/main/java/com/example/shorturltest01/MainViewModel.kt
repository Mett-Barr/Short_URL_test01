package com.example.shorturltest01

import android.webkit.URLUtil
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

const val BASE_URL = "http://tinyurl.com/api-create.php?url="
const val TEST_URL = "https://www.example.com/"

class MainViewModel : ViewModel() {
    var myUrl = mutableStateOf(TEST_URL)

    fun myUrlChange(str: String) {
        this.myUrl.value = str
    }

    var testTextFieldValue = mutableStateOf(TextFieldValue(TEST_URL))
        private set

    val testUrl = mutableStateOf(TextFieldValue(TEST_URL))

    fun testUrlChange(str: String) {
        this.testUrl.value = TextFieldValue(str)
    }

    fun isNetworkUrl() : Boolean = URLUtil.isNetworkUrl(this.testUrl.value.text)


    var shortUrl = mutableStateOf("")

    var networkState = mutableStateOf("")
}