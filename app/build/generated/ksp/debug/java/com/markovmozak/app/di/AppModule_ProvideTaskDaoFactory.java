package com.markovmozak.app.di;

import com.markovmozak.app.data.AppDatabase;
import com.markovmozak.app.data.TaskDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideTaskDaoFactory implements Factory<TaskDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideTaskDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TaskDao get() {
    return provideTaskDao(databaseProvider.get());
  }

  public static AppModule_ProvideTaskDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideTaskDaoFactory(databaseProvider);
  }

  public static TaskDao provideTaskDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTaskDao(database));
  }
}
