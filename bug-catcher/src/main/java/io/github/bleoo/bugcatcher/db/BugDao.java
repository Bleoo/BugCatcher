package io.github.bleoo.bugcatcher.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.github.bleoo.bugcatcher.model.Bug;

@Dao
public interface BugDao {

    @Query("SELECT * FROM bugs")
    List<Bug> getAll();

    @Insert
    void insertAll(Bug... bugs);

    @Delete
    void delete(Bug bug);
}