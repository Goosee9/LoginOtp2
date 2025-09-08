package com.example.loginotp2

import android.util.Log

object Logger {
    private const val BASE_TAG = "MyLog"

    fun e(tag: String, message: String) {
        Log.e("$BASE_TAG-$tag", message)
    }
    fun d(tag: String, message: String) {
        Log.d("$BASE_TAG-$tag", message)
    }

    fun i(tag: String, message: String) {
        Log.i("$BASE_TAG-$tag", message)
    }
}
