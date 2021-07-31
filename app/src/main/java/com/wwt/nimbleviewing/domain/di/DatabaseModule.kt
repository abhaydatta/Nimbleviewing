package com.wwt.nimbleviewing.domain.di

import android.app.Application
import androidx.room.Room
import com.wwt.nimbleviewing.data.db.AlbumDao
import com.wwt.nimbleviewing.data.db.AlbumDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): AlbumDatabase {
        return Room.databaseBuilder(application, AlbumDatabase::class.java, "album")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: AlbumDatabase): AlbumDao {
        return  database.getAlbumDAO()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}