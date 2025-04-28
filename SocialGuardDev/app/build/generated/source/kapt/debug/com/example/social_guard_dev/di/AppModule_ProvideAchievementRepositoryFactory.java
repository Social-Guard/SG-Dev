package com.example.social_guard_dev.di;

import android.content.Context;
import com.example.social_guard_dev.ui.data.AchievementRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AppModule_ProvideAchievementRepositoryFactory implements Factory<AchievementRepository> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideAchievementRepositoryFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AchievementRepository get() {
    return provideAchievementRepository(contextProvider.get());
  }

  public static AppModule_ProvideAchievementRepositoryFactory create(
      Provider<Context> contextProvider) {
    return new AppModule_ProvideAchievementRepositoryFactory(contextProvider);
  }

  public static AchievementRepository provideAchievementRepository(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAchievementRepository(context));
  }
}
