package com.jalpa.cavista.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jalpa.cavista.model.ImageItem;

/**
 * Database class with CommentsEntity table.
 */
@Database(entities = {CommentsEntity.class}, version = 1, exportSchema = false)
public abstract class CommentsDatabase extends RoomDatabase {
    public abstract CommentsDao CommentsDao();

}
