package com.markovmozak.app.ui.shopping;

import com.markovmozak.app.repository.ShoppingRepository;
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
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ShoppingViewModel_Factory implements Factory<ShoppingViewModel> {
  private final Provider<ShoppingRepository> shoppingRepositoryProvider;

  public ShoppingViewModel_Factory(Provider<ShoppingRepository> shoppingRepositoryProvider) {
    this.shoppingRepositoryProvider = shoppingRepositoryProvider;
  }

  @Override
  public ShoppingViewModel get() {
    return newInstance(shoppingRepositoryProvider.get());
  }

  public static ShoppingViewModel_Factory create(
      Provider<ShoppingRepository> shoppingRepositoryProvider) {
    return new ShoppingViewModel_Factory(shoppingRepositoryProvider);
  }

  public static ShoppingViewModel newInstance(ShoppingRepository shoppingRepository) {
    return new ShoppingViewModel(shoppingRepository);
  }
}
