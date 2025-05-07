package com.example.social_guard_dev.ui.viewmodels;

import android.app.Application;
import com.example.social_guard_dev.ui.data.AchievementRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class AchievementsViewModel_Factory implements Factory<AchievementsViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<AchievementRepository> repositoryProvider;

  public AchievementsViewModel_Factory(Provider<Application> applicationProvider,
      Provider<AchievementRepository> repositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AchievementsViewModel get() {
    return newInstance(applicationProvider.get(), repositoryProvider.get());
  }

  public static AchievementsViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<AchievementRepository> repositoryProvider) {
    return new AchievementsViewModel_Factory(applicationProvider, repositoryProvider);
  }

  public static AchievementsViewModel newInstance(Application application,
      AchievementRepository repository) {
    return new AchievementsViewModel(application, repository);
  }
}
