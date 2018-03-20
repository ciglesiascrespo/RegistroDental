package com.iglesias.c.registrodental.Utils;

import com.iglesias.c.registrodental.Pojo.Usuario;

/**
 * Created by Ciglesias on 18/03/2018.
 */

public class Singleton {

    public static Usuario usuarioActivo;

    public static void setUsuarioActivo(Usuario usuarioActivo) {
        Singleton.usuarioActivo = usuarioActivo;
    }

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }
}
