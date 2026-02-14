package com.markovmozak.app.di

import android.content.Context
import androidx.room.Room
import com.markovmozak.app.data.AppDatabase
import com.markovmozak.app.data.ShoppingDao
import com.markovmozak.app.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "markovmozak_db"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()

    @Provides
    fun provideShoppingDao(database: AppDatabase): ShoppingDao = database.shoppingDao()
}
