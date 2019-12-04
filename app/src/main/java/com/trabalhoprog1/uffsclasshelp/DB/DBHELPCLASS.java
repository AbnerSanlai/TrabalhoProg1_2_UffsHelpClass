package com.trabalhoprog1.uffsclasshelp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHELPCLASS extends SQLiteOpenHelper {

    public DBHELPCLASS(@Nullable Context context) {
        super(context, "DBHELPCLASS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDLL.getCriarTabelaClasse());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
