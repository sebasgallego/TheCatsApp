package com.aplicacion.thecatsapp.di

import com.aplicacion.thecatsapp.data.network.CatApiClient
import com.aplicacion.thecatsapp.utils.GlobalsVar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(GlobalsVar.HOST_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCatApiClient(retrofit: Retrofit):CatApiClient{
        return  retrofit.create(CatApiClient::class.java)
    }

}