package com.jalpa.cavista.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface CommentsDao {

    @Query("SELECT * FROM CommentsEntity WHERE id = :id" )
    List<CommentsEntity> getAllComments(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveComment(CommentsEntity task);
}
