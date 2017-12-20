package io.github.bleoo.bugcatcher.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by bleoo on 2017/12/15.
 */

@Entity(tableName = "bugs")
public class Bug {

    @PrimaryKey(autoGenerate = true)
    public int _id;

    public String id;
    public String info;

    @Ignore
    public Device device;

    public Bug() {
    }

    @Ignore
    public Bug(String id, String info) {
        this.id = id;
        this.info = info;
    }
}
