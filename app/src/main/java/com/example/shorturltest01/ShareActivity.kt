package com.example.shorturltest01

import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.shorturltest01.component.ShortenUrl
import com.example.shorturltest01.ui.theme.ShortURLTest01Theme

class ShareActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContent {
            ShortURLTest01Theme {
                ShortenUrl(viewModel) { getShortUrl(context = this, viewModel = viewModel) }
            }
        }
    }

    private fun init() {
        if (Intent.ACTION_SEND == intent.action && intent.type != null) {
            viewModel.myUrl.value = intent.getStringExtra(Intent.EXTRA_TEXT).toString()
            viewModel.testState.value = "Http：${URLUtil.isHttpUrl(viewModel.myUrl.value)}\n" +
                    "About：${URLUtil.isAboutUrl(viewModel.myUrl.value)}\n" +
                    "Content：${URLUtil.isContentUrl(viewModel.myUrl.value)}\n" +
                    "Network：${URLUtil.isNetworkUrl(viewModel.myUrl.value)}\n" +
                    "Https：${URLUtil.isHttpsUrl(viewModel.myUrl.value)}"
        }
    }
}

