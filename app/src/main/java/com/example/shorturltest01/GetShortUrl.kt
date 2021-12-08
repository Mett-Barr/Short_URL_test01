package com.example.shorturltest01

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

fun getShortUrl(context: Context, viewModel: MainViewModel) {
    val queue = Volley.newRequestQueue(context)

    val stringRequest = StringRequest(
        Request.Method.GET, BASE_URL + viewModel.myUrl.value,
        { response ->
            viewModel.shortUrl.value = response
        },
        {
            viewModel.shortUrl.value = it.toString()
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })

    // Add the request to the RequestQueue.
    queue.add(stringRequest)
}