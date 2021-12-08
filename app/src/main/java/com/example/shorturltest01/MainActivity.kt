package com.example.shorturltest01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shorturltest01.component.ShortenUrl
import com.example.shorturltest01.ui.theme.ShortURLTest01Theme

//import com.bitly.Bitly


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ShortURLTest01Theme {
                ShortenUrl(viewModel) { getShortUrl(context = this, viewModel = viewModel) }
            }
        }
    }
}