package com.example.social_guard_dev.di  // Update with your package name

import android.content.Context
import com.example.social_guard_dev.ui.data.AchievementRepository
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
    fun provideAchievementRepository(
        @ApplicationContext context: Context
    ): AchievementRepository {
        return AchievementRepository(context)
    }

    // Add other provider functions here as needed
}