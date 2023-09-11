package com.example.coinapp.base.di

import android.content.Context
import androidx.room.Room
import com.example.coinapp.base.models.APIResponse
import com.example.coinapp.base.models.ErrorDataModel
import com.example.coinapp.source.local.TransactionDao
import com.example.coinapp.source.local.TransactionsDatabase
import com.example.coinapp.source.remote.api.CoinsApi
import com.example.coinapp.source.remote.api.WalletApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient() =
        OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun getGson(): Gson {
        return Gson()
    }


    @Singleton
    @Provides
    fun getRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://octillionth-setups.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    suspend fun <T> getResponse(
        request: suspend () -> Response<T>
    ): APIResponse<T> {
        try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return APIResponse.success(result.body())
            } else {
                var message = "ERROR"
                val errorBody: ResponseBody? = result.errorBody()
                if (errorBody != null) {
                    val jsonString = errorBody.string()
                    val gson = Gson()
                    val errorResponse = gson.fromJson(jsonString, ErrorDataModel::class.java)
                    message = errorResponse?.message.toString()
                }

                return APIResponse.error(
                    message = message,
                    Exception(),
                )
            }
        } catch (e: Throwable) {
            return APIResponse.error("Unknown Error", Exception(e))
        }
    }

    @Provides
    @Singleton
    fun provideCoinsApi(retrofit: Retrofit): CoinsApi {
        return retrofit.create(CoinsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWalletApi(retrofit: Retrofit): WalletApi {
        return retrofit.create(WalletApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCustodialWalletDao(transactionsDatabase: TransactionsDatabase): TransactionDao =
        transactionsDatabase.transactionDao()

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            TransactionsDatabase::class.java,
            "transactions db"
        )
            .fallbackToDestructiveMigration()
            .build()
}