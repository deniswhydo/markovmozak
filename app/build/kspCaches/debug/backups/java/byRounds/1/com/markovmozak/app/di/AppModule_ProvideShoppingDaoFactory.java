package com.markovmozak.app.di;

import com.markovmozak.app.data.AppDatabase;
import com.markovmozak.app.data.ShoppingDao;
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
public final class AppModule_ProvideShoppingDaoFactory implements Factory<ShoppingDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideShoppingDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ShoppingDao get() {
    return provideShoppingDao(databaseProvider.get());
  }

  public static AppModule_ProvideShoppingDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideShoppingDaoFactory(databaseProvider);
  }

  public static ShoppingDao provideShoppingDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideShoppingDao(database));
  }
}
