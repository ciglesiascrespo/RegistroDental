package com.iglesias.c.registrodental.Presenter;

import com.iglesias.c.registrodental.Iterator.RegistrarIterator;
import com.iglesias.c.registrodental.Pojo.Usuario;
import com.iglesias.c.registrodental.View.RegistrarView;

/**
 * Created by Ciglesias on 18/03/2018.
 */

public class RegistrarPresenterImpl implements RegistrarPresenter {
    RegistrarView view;
    RegistrarIterator iterator;

    public RegistrarPresenterImpl(RegistrarView view) {
        this.view = view;
        iterator = new RegistrarIterator(this,view.getContext());
    }

    public void registrarUsuario(String nombre1, String nombre2, String apellido1,
                                 String apellido2, String telefono, String correo,
                                 String contrasena1, String contrasena2) {

        if (apellido1.isEmpty() || nombre1.isEmpty() || correo.isEmpty() || contrasena1.isEmpty() || contrasena2.isEmpty() || !contrasena1.equals(contrasena2)) {

            view.showTilErrorApellido(apellido1.isEmpty() ? "Campo requerido" : null);
            view.showTilErrorNombre(nombre1.isEmpty() ? "Campo requerido" : null);
            view.showTilErrorCorreo(correo.isEmpty() ? "Campo requerido" : null);
            view.showTilErrorContrasena1(contrasena1.isEmpty() ? "Campo requerido" : null);
            view.showTilErrorContrasena2(contrasena2.isEmpty() ? "Campo requerido" : null);
            view.showTilErrorContrasena2(!contrasena2.equals(contrasena1) ? "Las contraseñas no coinciden" : null);
        }else{
            Usuario usuario = new Usuario();
            usuario.setCorreo(correo);
            usuario.setNombre1(nombre1);
            usuario.setApellido1(apellido1);
            usuario.setApellido2(apellido2);
            usuario.setContrasena(contrasena1);
            usuario.setNombre2(nombre2);
            usuario.setTelefono(telefono);

            iterator.registrarUsuario(usuario);
        }
    }

    @Override
    public void onUsuarioRegistrado() {
        view.onUsuarioRegistrado();
    }

    @Override
    public void onError() {
        view.showErrorRegistrar("Error registrando usuario.");
    }

    @Override
    public void onUsuarioExiste() {
        view.showErrorRegistrar("El correo ya se encuentra registrado.");
    }
}
