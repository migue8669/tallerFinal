package me.parzibyte.crudsqlite.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import me.parzibyte.crudsqlite.AyudanteBaseDeDatos;
import me.parzibyte.crudsqlite.modelos.Mascota;


public class MascotasController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "tblestudiantes";

    public MascotasController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }


    public int eliminarMascota(Mascota mascota) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(mascota.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaMascota(Mascota mascota) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", mascota.getNombre());
        valoresParaInsertar.put("codigo", mascota.getCodigo());
        valoresParaInsertar.put("grado", mascota.getGrado());
        valoresParaInsertar.put("nota1", mascota.getNota1());
        valoresParaInsertar.put("nota2", mascota.getNota2());
        valoresParaInsertar.put("nota3", mascota.getNota3());
        valoresParaInsertar.put("promedio", mascota.getPromedio());
                valoresParaInsertar.put("promedio", mascota.getId());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);

    }

    public int guardarCambios(Mascota mascotaEditada) {
        System.out.println("guardar cambios" +mascotaEditada );
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();

        valoresParaActualizar.put("nombre", mascotaEditada.getNombre());
        valoresParaActualizar.put("nota1", mascotaEditada.getNota1());
        valoresParaActualizar.put("nota2", mascotaEditada.getNota2());
        valoresParaActualizar.put("nota3", mascotaEditada.getNota3());

        // where id...

        String campoParaActualizar = "id = ?";
        System.out.println( campoParaActualizar);
        // ... = idMascota
        String[] argumentosParaActualizar = {String.valueOf(mascotaEditada.getId())};
        System.out.println( argumentosParaActualizar);
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();

        String[] columnasAConsultar = {"nombre","codigo","grado", "nota1", "nota2", "nota3", "promedio" ,"id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,//from tblestudiantes
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return mascotas;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return mascotas;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            String nombreObtenidoDeBD = cursor.getString(0);
            int  codigoO = cursor.getInt(1);
            String gradoO = cursor.getString(2);
            int  nota1o = cursor.getInt(3);
            int  nota2o = cursor.getInt(4);
            int  nota3o = cursor.getInt(5);
            int  promedio = cursor.getInt(6);
            long id = cursor.getLong(7);
            Mascota mascotaObtenidaDeBD = new Mascota(nombreObtenidoDeBD, codigoO,gradoO,nota1o,nota2o,nota3o,promedio, id);
           System.out.println( mascotaObtenidaDeBD );
            mascotas.add(mascotaObtenidaDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return mascotas;
    }
}