package io.github.bleoo.bugcatcher.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.github.bleoo.bugcatcher.model.Bug;

/**
 * Created by bleoo on 2017/12/19.
 */

public class DBHelper {

    private static AppDatabase sDb;

    public static void init(Context context) {
        sDb = Room.databaseBuilder(context, AppDatabase.class, "bug-catcher").build();
    }

    public static List<Bug> getBugs() {
        if (sDb == null) {
            return null;
        }
        return sDb.bugDao().getAll();
    }

    public static void insertBug(Bug bug) {
        if (sDb == null) {
            return;
        }
        sDb.bugDao().insertAll(bug);
    }

    public static void deleteBug(Bug bug) {
        if (sDb == null) {
            return;
        }
        sDb.bugDao().delete(bug);
    }
}
