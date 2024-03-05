package com.rocknhoney.matcheslistingapp.core.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.rocknhoney.matcheslistingapp.BuildConfig
import com.rocknhoney.matcheslistingapp.core.api.MatchesApiService
import com.rocknhoney.matcheslistingapp.core.api.repository.MatchesRepository
import com.rocknhoney.matcheslistingapp.core.api.repository.MatchesRepositoryInterface
import com.rocknhoney.matcheslistingapp.core.data.roomdb.dao.MatchDao
import com.rocknhoney.matcheslistingapp.core.data.roomdb.database.MatchesListingAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ): MatchesListingAppDatabase {
        return Room.databaseBuilder(
            context,
            MatchesListingAppDatabase::class.java,
            "matcheslistingapp-database"
        ).build()
    }

    @Singleton
    @Provides
    fun injectMatchDao(database: MatchesListingAppDatabase) = database.MatchDao()


    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providePostApiService(retrofit: Retrofit): MatchesApiService =
        retrofit.create(MatchesApiService::class.java)

    @Singleton
    @Provides
    fun providePostRepository(apiService: MatchesApiService, matchDao: MatchDao) =
        MatchesRepository(apiService, matchDao) as MatchesRepositoryInterface

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

}