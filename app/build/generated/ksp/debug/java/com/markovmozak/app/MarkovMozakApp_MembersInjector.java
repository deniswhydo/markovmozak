package com.markovmozak.app;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MarkovMozakApp_MembersInjector implements MembersInjector<MarkovMozakApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public MarkovMozakApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<MarkovMozakApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new MarkovMozakApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(MarkovMozakApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.markovmozak.app.MarkovMozakApp.workerFactory")
  public static void injectWorkerFactory(MarkovMozakApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
