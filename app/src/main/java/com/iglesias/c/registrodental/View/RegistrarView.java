package com.iglesias.c.registrodental.View;

import android.content.Context;

/**
 * Created by Ciglesias on 18/03/2018.
 */

public interface RegistrarView {
    void showErrorRegistrar(String msj);

    Context getContext();

    void showTilErrorNombre(String msj);

    void showTilErrorApellido(String msj);

    void showTilErrorCorreo(String msj);

    void showTilErrorContrasena1(String msj);

    void showTilErrorContrasena2(String msj);

    void onUsuarioRegistrado();
}
