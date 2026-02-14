package com.markovmozak.app.notifications;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.markovmozak.app.repository.TaskRepository;
import dagger.internal.DaggerGenerated;
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
public final class ReminderWorker_Factory {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public ReminderWorker_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  public ReminderWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, taskRepositoryProvider.get());
  }

  public static ReminderWorker_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new ReminderWorker_Factory(taskRepositoryProvider);
  }

  public static ReminderWorker newInstance(Context context, WorkerParameters params,
      TaskRepository taskRepository) {
    return new ReminderWorker(context, params, taskRepository);
  }
}
