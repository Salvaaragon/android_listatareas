package com.salvaaragon.rememberapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Salva Aragón
 */
public class DataBaseTasks extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1; // Versión de la base de datos en la que estamos
    public static final String DATABASE_NAME = "Tasks.db"; // Nombre de la base de datos

    public DataBaseTasks(Context context) {
        // Contexto de la acción, nombre de la base de datos y versión (necesarios para ejecutar la BD)
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Aquí añadimos código SQL que funciona como lector de consulta de la BD
        // Código para crear BD
        db.execSQL("CREATE TABLE Tarea (" +
                   "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "nombre VARCHAR NOT NULL, " +
                   "fecha VARCHAR NOT NULL, " +
                   "descripcion VARCHAR NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí añadimos código SQL para actualizar la BD (Estructura o modelo como tal)
    }

    public void guardaDatos(String task, String date, String desc) {
        getReadableDatabase().execSQL("INSERT INTO Tarea VALUES ("+null+", '"+task+"', '"+date+"', '"+desc+"');");
    }

    public Cursor getTareas() {
        return getReadableDatabase().query("Tarea", null, null, null, null, null, null);
    }

    public Cursor getTaskById(String id) {
        return getReadableDatabase().rawQuery("SELECT * FROM Tarea WHERE id = "+id+";", null);
    }

    public void updateTask(String id, String nombre, String fecha, String desc) {
        getReadableDatabase().execSQL("UPDATE Tarea " +
                                            "SET nombre = '"+nombre+"', fecha = '"+fecha+"', descripcion = '"+desc+"'" +
                                            "WHERE id = "+id+";");
    }
}
