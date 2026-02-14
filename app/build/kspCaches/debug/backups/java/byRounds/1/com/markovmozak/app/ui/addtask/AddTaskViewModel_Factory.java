package com.markovmozak.app.ui.addtask;

import com.markovmozak.app.repository.TaskRepository;
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
public final class AddTaskViewModel_Factory implements Factory<AddTaskViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public AddTaskViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public AddTaskViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static AddTaskViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new AddTaskViewModel_Factory(taskRepositoryProvider);
  }

  public static AddTaskViewModel newInstance(TaskRepository taskRepository) {
    return new AddTaskViewModel(taskRepository);
  }
}
