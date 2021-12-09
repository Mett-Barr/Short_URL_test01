package com.example.shorturltest01

import android.content.Intent
import android.os.Bundle
import android.webkit.URLUtil
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
                ShortenUrl(viewModel, viewModel::testUrlChange) { getShortUrl(context = this, viewModel = viewModel) }
            }
        }
    }

    private fun init() {
        if (Intent.ACTION_SEND == intent.action && intent.type != null) {
            viewModel.myUrl.value = intent.getStringExtra(Intent.EXTRA_TEXT).toString()
            viewModel.testUrlChange(intent.getStringExtra(Intent.EXTRA_TEXT).toString())
            viewModel.networkState.value = "Http：${URLUtil.isHttpUrl(viewModel.testUrl.value.text)}\n" +
                    "About：${URLUtil.isAboutUrl(viewModel.testUrl.value.text)}\n" +
                    "Content：${URLUtil.isContentUrl(viewModel.testUrl.value.text)}\n" +
                    "Network：${URLUtil.isNetworkUrl(viewModel.testUrl.value.text)}\n" +
                    "Https：${URLUtil.isHttpsUrl(viewModel.testUrl.value.text)}\n" +
                    viewModel.testUrl.value.text + "\n" + viewModel.isNetworkUrl()
        }
    }
}

