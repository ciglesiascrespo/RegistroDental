package com.iglesias.c.registrodental.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.iglesias.c.registrodental.Pojo.Usuario;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class DbHandler {

    private DbHelper dbHelper;
    private static DbHandler instance = null;
    public String TAG = getClass().getName();

    protected DbHandler(Context context) {

        dbHelper = new DbHelper(context);
    }

    public static DbHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DbHandler(context);
        }

        return instance;
    }

    public Usuario verificaUsuario(String usr, String pass) {
        Usuario usuario = new Usuario();
        Cursor c = null;

        try {
            int idUsuario = -1;
            String apellido1 = "", nombre1 = "", correo = "";

            String query = "Select " + UserDb.KEY_ID + ", " + UserDb.KEY_APELLIDO1 + "," + UserDb.KEY_CORREO + ",  " +
                    UserDb.KEY_NOMBRE1 +
                    " From " + UserDb.TABLE +
                    " where " + UserDb.KEY_CONTRASENA + " = '" + pass + "' and " + UserDb.KEY_CORREO + " = '" + usr + "';";

            Log.e(TAG, "sql login: " + query);
            c = dbHelper.execSql(query);

            if (c.moveToFirst()) {
                if (!c.isNull(c.getColumnIndex(UserDb.KEY_ID))) {
                    idUsuario = c.getInt(c.getColumnIndex(UserDb.KEY_ID));
                }

                if (!c.isNull(c.getColumnIndex(UserDb.KEY_NOMBRE1))) {
                    nombre1 = c.getString(c.getColumnIndex(UserDb.KEY_NOMBRE1));
                }

                if (!c.isNull(c.getColumnIndex(UserDb.KEY_APELLIDO1))) {
                    apellido1 = c.getString(c.getColumnIndex(UserDb.KEY_APELLIDO1));
                }
                if (!c.isNull(c.getColumnIndex(UserDb.KEY_CORREO))) {
                    correo = c.getString(c.getColumnIndex(UserDb.KEY_CORREO));
                }
            }


            usuario.setIdUsuario(idUsuario);
            usuario.setApellido1(apellido1);
            usuario.setNombre1(nombre1);
            usuario.setCorreo(correo);


        } catch (Exception e) {
            if (c != null && !c.isClosed()) {
                c.close();
            }
            Log.e(TAG, "Error validando usuario: " + e.getMessage());
            e.printStackTrace();
        }


        return usuario;
    }

    public boolean registrarUsuario(Usuario usuario) {
        boolean result = false;

        ContentValues cv = new ContentValues();

        cv.put(UserDb.KEY_NOMBRE1, usuario.getNombre1());
        cv.put(UserDb.KEY_NOMBRE2, usuario.getNombre2());
        cv.put(UserDb.KEY_APELLIDO1, usuario.getApellido1());
        cv.put(UserDb.KEY_APELLIDO2, usuario.getApellido2());
        cv.put(UserDb.KEY_CORREO, usuario.getCorreo());
        cv.put(UserDb.KEY_TELEFONO, usuario.getTelefono());
        cv.put(UserDb.KEY_CONTRASENA, usuario.getContrasena());
        try {
            result = dbHelper.insert(UserDb.TABLE, cv) > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error registrando usuario");
            e.printStackTrace();

            result = false;
        }

        return result;
    }

    public boolean verificaUsuarioExistente(String correo) {
        boolean result = false;

        Cursor c = null;

        try {
            int nro = 0;
            String query = "Select count(*) nro" +
                    " From " + UserDb.TABLE +
                    " where " + UserDb.KEY_CORREO + " = '" + correo + "';";

            c = dbHelper.execSql(query);

            if (c.moveToFirst()) {


                if (!c.isNull(c.getColumnIndex("nro"))) {
                    nro = c.getInt(c.getColumnIndex("nro"));
                }

            }


            result = nro > 0;


        } catch (Exception e) {
            if (c != null && !c.isClosed()) {
                c.close();
            }
            Log.e(TAG, "Error validando usuario: " + e.getMessage());
            e.printStackTrace();
        }


        return result;
    }


}
