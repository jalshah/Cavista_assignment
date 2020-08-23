package com.jalpa.cavista.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Comments table to store comments on image.
 */

@Entity()
public class CommentsEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int row_id;

    @NonNull
    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "comments")
    public String comment;
}
