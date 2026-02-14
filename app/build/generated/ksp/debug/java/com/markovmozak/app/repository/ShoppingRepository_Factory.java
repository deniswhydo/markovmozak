package com.markovmozak.app.repository;

import com.markovmozak.app.data.ShoppingDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ShoppingRepository_Factory implements Factory<ShoppingRepository> {
  private final Provider<ShoppingDao> shoppingDaoProvider;

  public ShoppingRepository_Factory(Provider<ShoppingDao> shoppingDaoProvider) {
    this.shoppingDaoProvider = shoppingDaoProvider;
  }

  @Override
  public ShoppingRepository get() {
    return newInstance(shoppingDaoProvider.get());
  }

  public static ShoppingRepository_Factory create(Provider<ShoppingDao> shoppingDaoProvider) {
    return new ShoppingRepository_Factory(shoppingDaoProvider);
  }

  public static ShoppingRepository newInstance(ShoppingDao shoppingDao) {
    return new ShoppingRepository(shoppingDao);
  }
}
