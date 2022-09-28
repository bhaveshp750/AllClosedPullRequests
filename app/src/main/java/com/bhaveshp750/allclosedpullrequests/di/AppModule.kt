package com.bhaveshp750.allclosedpullrequests.di

import com.bhaveshp750.allclosedpullrequests.modal.PullRequestsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePullRequest(): PullRequestsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PullRequestsApi.BASE_URL)
            .build()
            .create(PullRequestsApi::class.java)
    }
}