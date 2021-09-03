package com.android.example.stackoverflowwork.di

import com.android.example.stackoverflowwork.data.service.RemoteApiService
import com.android.example.stackoverflowwork.util.Constants
import com.android.example.stackoverflowwork.util.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideBaseUrl()=Constants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson,BASE_URL:String):Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit.Builder):RemoteApiService{
        return retrofit
            .build()
            .create(RemoteApiService::class.java)
    }
}