package me.parzibyte.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DE_DATOS = "bdestudiantes ",
            NOMBRE_TABLA_MASCOTAS = "tblestudiantes";
    private static final int VERSION_BASE_DE_DATOS = 4;

    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre text, codigo int, grado text, nota1 int,nota2 int,nota3 int, promedio double)", NOMBRE_TABLA_MASCOTAS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
