package com.example.coinapp.base.utils

import com.example.coinapp.base.models.CoinData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    inline fun <reified T> T.toJson(): String = Gson().toJson(this)

    inline fun <reified T> String.toObject(): T = Gson().fromJson(this, T::class.java)

    //Convert Coin to Json String
    fun setCoin(result: CoinData): String {
        val gson = Gson()
        return gson.toJson(result)
    }

    //Get Coin From Json String
    fun getCoin(result: String): CoinData {
        val gson = Gson()
        return gson.fromJson(
            result, object : TypeToken<CoinData>() {}.type
        )
    }

}

