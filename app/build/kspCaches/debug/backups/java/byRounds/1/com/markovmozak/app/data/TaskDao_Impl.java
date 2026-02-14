package com.markovmozak.app.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Task> __insertionAdapterOfTask;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<Task> __deletionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<Task> __updateAdapterOfTask;

  private final SharedSQLiteStatement __preparedStmtOfCompleteTask;

  private final SharedSQLiteStatement __preparedStmtOfUncompleteTask;

  private final SharedSQLiteStatement __preparedStmtOfUpdateEscalationLevel;

  public TaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tasks` (`id`,`title`,`description`,`category`,`priority`,`dueDate`,`dueTime`,`isCompleted`,`isRecurring`,`recurringDays`,`reminderEnabled`,`escalationLevel`,`createdAt`,`completedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final String _tmp = __converters.fromCategory(entity.getCategory());
        statement.bindString(4, _tmp);
        final String _tmp_1 = __converters.fromPriority(entity.getPriority());
        statement.bindString(5, _tmp_1);
        if (entity.getDueDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDueDate());
        }
        if (entity.getDueTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDueTime());
        }
        final int _tmp_2 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        final int _tmp_3 = entity.isRecurring() ? 1 : 0;
        statement.bindLong(9, _tmp_3);
        if (entity.getRecurringDays() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getRecurringDays());
        }
        final int _tmp_4 = entity.getReminderEnabled() ? 1 : 0;
        statement.bindLong(11, _tmp_4);
        statement.bindLong(12, entity.getEscalationLevel());
        statement.bindLong(13, entity.getCreatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getCompletedAt());
        }
      }
    };
    this.__deletionAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tasks` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`description` = ?,`category` = ?,`priority` = ?,`dueDate` = ?,`dueTime` = ?,`isCompleted` = ?,`isRecurring` = ?,`recurringDays` = ?,`reminderEnabled` = ?,`escalationLevel` = ?,`createdAt` = ?,`completedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final String _tmp = __converters.fromCategory(entity.getCategory());
        statement.bindString(4, _tmp);
        final String _tmp_1 = __converters.fromPriority(entity.getPriority());
        statement.bindString(5, _tmp_1);
        if (entity.getDueDate() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDueDate());
        }
        if (entity.getDueTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDueTime());
        }
        final int _tmp_2 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        final int _tmp_3 = entity.isRecurring() ? 1 : 0;
        statement.bindLong(9, _tmp_3);
        if (entity.getRecurringDays() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getRecurringDays());
        }
        final int _tmp_4 = entity.getReminderEnabled() ? 1 : 0;
        statement.bindLong(11, _tmp_4);
        statement.bindLong(12, entity.getEscalationLevel());
        statement.bindLong(13, entity.getCreatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getCompletedAt());
        }
        statement.bindLong(15, entity.getId());
      }
    };
    this.__preparedStmtOfCompleteTask = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET isCompleted = 1, completedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUncompleteTask = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET isCompleted = 0, completedAt = NULL WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateEscalationLevel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET escalationLevel = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTask(final Task task, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTask.insertAndReturnId(task);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTask(final Task task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTask.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTask(final Task task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTask.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object completeTask(final long id, final long completedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCompleteTask.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, completedAt);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfCompleteTask.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object uncompleteTask(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUncompleteTask.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUncompleteTask.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateEscalationLevel(final long id, final int level,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateEscalationLevel.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, level);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateEscalationLevel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Task>> getAllTasks() {
    final String _sql = "SELECT * FROM tasks ORDER BY isCompleted ASC, priority DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTime");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecurring");
          final int _cursorIndexOfRecurringDays = CursorUtil.getColumnIndexOrThrow(_cursor, "recurringDays");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfEscalationLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "escalationLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Category _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toCategory(_tmp);
            final Priority _tmpPriority;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp_1);
            final String _tmpDueDate;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmpDueDate = null;
            } else {
              _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            }
            final String _tmpDueTime;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmpDueTime = null;
            } else {
              _tmpDueTime = _cursor.getString(_cursorIndexOfDueTime);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final boolean _tmpIsRecurring;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_3 != 0;
            final Integer _tmpRecurringDays;
            if (_cursor.isNull(_cursorIndexOfRecurringDays)) {
              _tmpRecurringDays = null;
            } else {
              _tmpRecurringDays = _cursor.getInt(_cursorIndexOfRecurringDays);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_4 != 0;
            final int _tmpEscalationLevel;
            _tmpEscalationLevel = _cursor.getInt(_cursorIndexOfEscalationLevel);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpPriority,_tmpDueDate,_tmpDueTime,_tmpIsCompleted,_tmpIsRecurring,_tmpRecurringDays,_tmpReminderEnabled,_tmpEscalationLevel,_tmpCreatedAt,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Task>> getActiveTasks() {
    final String _sql = "SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY priority DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTime");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecurring");
          final int _cursorIndexOfRecurringDays = CursorUtil.getColumnIndexOrThrow(_cursor, "recurringDays");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfEscalationLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "escalationLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Category _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toCategory(_tmp);
            final Priority _tmpPriority;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp_1);
            final String _tmpDueDate;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmpDueDate = null;
            } else {
              _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            }
            final String _tmpDueTime;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmpDueTime = null;
            } else {
              _tmpDueTime = _cursor.getString(_cursorIndexOfDueTime);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final boolean _tmpIsRecurring;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_3 != 0;
            final Integer _tmpRecurringDays;
            if (_cursor.isNull(_cursorIndexOfRecurringDays)) {
              _tmpRecurringDays = null;
            } else {
              _tmpRecurringDays = _cursor.getInt(_cursorIndexOfRecurringDays);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_4 != 0;
            final int _tmpEscalationLevel;
            _tmpEscalationLevel = _cursor.getInt(_cursorIndexOfEscalationLevel);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpPriority,_tmpDueDate,_tmpDueTime,_tmpIsCompleted,_tmpIsRecurring,_tmpRecurringDays,_tmpReminderEnabled,_tmpEscalationLevel,_tmpCreatedAt,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Task>> getTasksForDate(final String date) {
    final String _sql = "SELECT * FROM tasks WHERE isCompleted = 0 AND dueDate = ? ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTime");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecurring");
          final int _cursorIndexOfRecurringDays = CursorUtil.getColumnIndexOrThrow(_cursor, "recurringDays");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfEscalationLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "escalationLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Category _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toCategory(_tmp);
            final Priority _tmpPriority;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp_1);
            final String _tmpDueDate;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmpDueDate = null;
            } else {
              _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            }
            final String _tmpDueTime;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmpDueTime = null;
            } else {
              _tmpDueTime = _cursor.getString(_cursorIndexOfDueTime);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final boolean _tmpIsRecurring;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_3 != 0;
            final Integer _tmpRecurringDays;
            if (_cursor.isNull(_cursorIndexOfRecurringDays)) {
              _tmpRecurringDays = null;
            } else {
              _tmpRecurringDays = _cursor.getInt(_cursorIndexOfRecurringDays);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_4 != 0;
            final int _tmpEscalationLevel;
            _tmpEscalationLevel = _cursor.getInt(_cursorIndexOfEscalationLevel);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpPriority,_tmpDueDate,_tmpDueTime,_tmpIsCompleted,_tmpIsRecurring,_tmpRecurringDays,_tmpReminderEnabled,_tmpEscalationLevel,_tmpCreatedAt,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Task>> getTasksByCategory(final Category category) {
    final String _sql = "SELECT * FROM tasks WHERE isCompleted = 0 AND category = ? ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromCategory(category);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTime");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecurring");
          final int _cursorIndexOfRecurringDays = CursorUtil.getColumnIndexOrThrow(_cursor, "recurringDays");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfEscalationLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "escalationLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Category _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toCategory(_tmp_1);
            final Priority _tmpPriority;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp_2);
            final String _tmpDueDate;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmpDueDate = null;
            } else {
              _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            }
            final String _tmpDueTime;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmpDueTime = null;
            } else {
              _tmpDueTime = _cursor.getString(_cursorIndexOfDueTime);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final boolean _tmpIsRecurring;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_4 != 0;
            final Integer _tmpRecurringDays;
            if (_cursor.isNull(_cursorIndexOfRecurringDays)) {
              _tmpRecurringDays = null;
            } else {
              _tmpRecurringDays = _cursor.getInt(_cursorIndexOfRecurringDays);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final int _tmpEscalationLevel;
            _tmpEscalationLevel = _cursor.getInt(_cursorIndexOfEscalationLevel);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpPriority,_tmpDueDate,_tmpDueTime,_tmpIsCompleted,_tmpIsRecurring,_tmpRecurringDays,_tmpReminderEnabled,_tmpEscalationLevel,_tmpCreatedAt,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTaskById(final long id, final Continuation<? super Task> $completion) {
    final String _sql = "SELECT * FROM tasks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Task>() {
      @Override
      @Nullable
      public Task call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTime");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecurring");
          final int _cursorIndexOfRecurringDays = CursorUtil.getColumnIndexOrThrow(_cursor, "recurringDays");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfEscalationLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "escalationLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final Task _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Category _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toCategory(_tmp);
            final Priority _tmpPriority;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp_1);
            final String _tmpDueDate;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmpDueDate = null;
            } else {
              _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            }
            final String _tmpDueTime;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmpDueTime = null;
            } else {
              _tmpDueTime = _cursor.getString(_cursorIndexOfDueTime);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final boolean _tmpIsRecurring;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_3 != 0;
            final Integer _tmpRecurringDays;
            if (_cursor.isNull(_cursorIndexOfRecurringDays)) {
              _tmpRecurringDays = null;
            } else {
              _tmpRecurringDays = _cursor.getInt(_cursorIndexOfRecurringDays);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_4 != 0;
            final int _tmpEscalationLevel;
            _tmpEscalationLevel = _cursor.getInt(_cursorIndexOfEscalationLevel);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _result = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpPriority,_tmpDueDate,_tmpDueTime,_tmpIsCompleted,_tmpIsRecurring,_tmpRecurringDays,_tmpReminderEnabled,_tmpEscalationLevel,_tmpCreatedAt,_tmpCompletedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> getActiveTaskCount() {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTasksWithReminders(final Continuation<? super List<Task>> $completion) {
    final String _sql = "SELECT * FROM tasks WHERE reminderEnabled = 1 AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDueTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dueTime");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfIsRecurring = CursorUtil.getColumnIndexOrThrow(_cursor, "isRecurring");
          final int _cursorIndexOfRecurringDays = CursorUtil.getColumnIndexOrThrow(_cursor, "recurringDays");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfEscalationLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "escalationLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Category _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toCategory(_tmp);
            final Priority _tmpPriority;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toPriority(_tmp_1);
            final String _tmpDueDate;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmpDueDate = null;
            } else {
              _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            }
            final String _tmpDueTime;
            if (_cursor.isNull(_cursorIndexOfDueTime)) {
              _tmpDueTime = null;
            } else {
              _tmpDueTime = _cursor.getString(_cursorIndexOfDueTime);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_2 != 0;
            final boolean _tmpIsRecurring;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRecurring);
            _tmpIsRecurring = _tmp_3 != 0;
            final Integer _tmpRecurringDays;
            if (_cursor.isNull(_cursorIndexOfRecurringDays)) {
              _tmpRecurringDays = null;
            } else {
              _tmpRecurringDays = _cursor.getInt(_cursorIndexOfRecurringDays);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_4 != 0;
            final int _tmpEscalationLevel;
            _tmpEscalationLevel = _cursor.getInt(_cursorIndexOfEscalationLevel);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCategory,_tmpPriority,_tmpDueDate,_tmpDueTime,_tmpIsCompleted,_tmpIsRecurring,_tmpRecurringDays,_tmpReminderEnabled,_tmpEscalationLevel,_tmpCreatedAt,_tmpCompletedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
