package com.wwt.nimbleviewing.domain.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.wwt.nimbleviewing.BuildConfig
import com.wwt.nimbleviewing.data.api.ApiHelper
import com.wwt.nimbleviewing.data.api.ApiHelperImpl
import com.wwt.nimbleviewing.data.api.ApiService
import com.wwt.nimbleviewing.data.db.AlbumDao
import com.wwt.nimbleviewing.data.db.AlbumDatabase
import com.wwt.nimbleviewing.data.util.NetworkHelper
import com.wwt.nimbleviewing.data.util.getUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get(),get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        . readTimeout(2000L,TimeUnit.MILLISECONDS)
        .connectTimeout(2000L,TimeUnit.MILLISECONDS)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL.getUrl())
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

private fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

