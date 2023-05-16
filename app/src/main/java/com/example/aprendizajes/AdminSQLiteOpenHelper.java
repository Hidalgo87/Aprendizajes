package com.example.aprendizajes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.TestOnly;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table estudiantes (nombre text, password text, edad text, correo text," +
                "genero text, etnia text, estrato text, ingresos_familiares text," +
                " k1a text, k1b text, k1c text, k1d text," +
                " k2a text, k2b text, k2c text, k2d text," +
                " k3a text, k3b text, k3c text, k3d text," +
                " k4a text, k4b text, k4c text, k4d text," +
                " k5a text, k5b text, k5c text, k5d text," +
                " k6a text, k6b text, k6c text, k6d text," +
                " k7a text, k7b text, k7c text, k7d text," +
                " k8a text, k8b text, k8c text, k8d text," +
                " k9a text, k9b text, k9c text, k9d text," +
                " k10a text, k10b text, k10c text, k10d text," +
                " k11a text, k11b text, k11c text, k11d text," +
                " k12a text, k12b text, k12c text, k12d text," +
                " v1a text, v1b text, v1c text, v1d text," +
                " v2a text, v2b text, v2c text, v2d text," +
                " v3a text, v3b text, v3c text, v3d text," +
                " v4a text, v4b text, v4c text, v4d text," +
                " v5a text, v5b text, v5c text, v5d text," +
                " v6a text, v6b text, v6c text, v6d text," +
                " v7a text, v7b text, v7c text, v7d text," +
                " v8a text, v8b text, v8c text, v8d text," +
                " v9a text, v9b text, v9c text, v9d text," +
                " v10a text, v10b text, v10c text, v10d text," +
                " v11a text, v11b text, v11c text, v11d text," +
                " v12a text, v12b text, v12c text, v12d text," +
                " v13a text, v13b text, v13c text, v13d text," +
                " v14a text, v14b text, v14c text, v14d text," +
                " v15a text, v15b text, v15c text, v15d text," +
                " v16a text, v16b text, v16c text, v16d text," +
                " estiloKOLB text, estiloVARK text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
