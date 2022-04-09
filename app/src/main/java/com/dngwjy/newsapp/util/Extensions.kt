package com.dngwjy.newsapp.util

import android.content.Context
import android.util.Log
import android.widget.Toast

inline fun <reified T>T.logE(msg:String) = msg.let{
    Log.e(T::class.java.simpleName,it)
}

inline fun <reified T>T.logD(msg:String) = msg.let{
    Log.d(T::class.java.simpleName,it)
}

fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception("Koneksi Internet Tidak Ada!"))
}
fun Context.toast(msg:String) {
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
