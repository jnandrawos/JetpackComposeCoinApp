package com.example.coinapp.base.extensions

fun String.encodeUrl(): String {
    return this.replace("/", "%2F")
}

fun String.decodeUrl(): String {
    return this.replace("%2F", "/")
}