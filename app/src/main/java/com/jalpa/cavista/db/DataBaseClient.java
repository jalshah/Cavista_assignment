package com.jalpa.cavista.db;

import android.content.Context;

import androidx.room.Room;

public class DataBaseClient {
        private static DataBaseClient mInstance;

        // app database object
        private CommentsDatabase appDatabase;

        private DataBaseClient(Context mCtx) {
            //creating the app database with Room database builder
            appDatabase = Room.databaseBuilder(mCtx, CommentsDatabase.class, "CommentsDB").allowMainThreadQueries().build();
        }

        public static synchronized DataBaseClient getInstance(Context mCtx) {
            if (mInstance == null) {
                mInstance = new DataBaseClient(mCtx);
            }
            return mInstance;
        }

        public CommentsDatabase getAppDatabase() {
            return appDatabase;
        }



}
