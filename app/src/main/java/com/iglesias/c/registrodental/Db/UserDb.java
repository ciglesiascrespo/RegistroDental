package com.iglesias.c.registrodental.Db;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class UserDb {
    public static final String TABLE = "usuarios";

    public static final String KEY_ID = "id_usuario";
    public static final String KEY_NOMBRE1 = "nombre1";
    public static final String KEY_NOMBRE2 = "nombre2";
    public static final String KEY_APELLIDO1 = "apellido1";
    public static final String KEY_APELLIDO2 = "apellido2";
    public static final String KEY_CORREO = "correo";
    public static final String KEY_CONTRASENA = "contrasena";
    public static final String KEY_TELEFONO = "telefono";


    public static final int ID_USUARIO_CyM = 1;

    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` " +
                " (`" + KEY_ID + "` INTEGER, `" + KEY_NOMBRE1 + "` TEXT, `" + KEY_NOMBRE2 + "` TEXT, `"
                + KEY_APELLIDO1 + "` TEXT, `" + KEY_APELLIDO2 + "` TEXT, `"+ KEY_CORREO+"` TEXT, `"
                + KEY_CONTRASENA+"` TEXT, `"+ KEY_TELEFONO+"` TEXT, " +
                "PRIMARY KEY(`" + KEY_ID + "`) )";

        return query;
    }
}
