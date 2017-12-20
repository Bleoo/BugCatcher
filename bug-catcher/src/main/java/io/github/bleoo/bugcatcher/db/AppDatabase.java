package io.github.bleoo.bugcatcher.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.github.bleoo.bugcatcher.model.Bug;

@Database(entities = {Bug.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BugDao bugDao();
}