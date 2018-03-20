package com.iglesias.c.registrodental.Activity;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iglesias.c.registrodental.Presenter.RegistrarPresenterImpl;
import com.iglesias.c.registrodental.R;
import com.iglesias.c.registrodental.View.RegistrarView;

public class RegistrarActivity extends AppCompatActivity implements RegistrarView {

    TextInputLayout tilNombre, tilApellido, tilCorreo, tilContrasena1, tilContrasena2;
    TextInputEditText edtNombre1, edtNombre2, edtApellido1, edtApellido2, edtTelefono, edtCorreo, edtContrasena1, edtContrasena2;
    Button btnRegistrar;

    RegistrarPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        setupViews();
        presenter = new RegistrarPresenterImpl(this);

    }

    private void setupViews() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Registrar usuario");

        tilApellido = findViewById(R.id.id_til_registrar_apellido);
        tilContrasena1 = findViewById(R.id.id_til_registrar_contrasena1);
        tilContrasena2 = findViewById(R.id.id_til_registrar_contrasena2);
        tilCorreo = findViewById(R.id.id_til_registrar_correo);
        tilNombre = findViewById(R.id.id_til_registrar_nombre);

        edtApellido1 = findViewById(R.id.id_edt_registrar_apellido1);
        edtApellido2 = findViewById(R.id.id_edt_registrar_apellido2);
        edtContrasena1 = findViewById(R.id.id_edt_registrar_contrasena1);
        edtContrasena2 = findViewById(R.id.id_edt_registrar_contrasena2);
        edtNombre1 = findViewById(R.id.id_edt_registrar_nombre1);
        edtNombre2 = findViewById(R.id.id_edt_registrar_nombre2);
        edtCorreo = findViewById(R.id.id_edt_registrar_correo);
        edtTelefono = findViewById(R.id.id_edt_registrar_telefono);

        btnRegistrar = findViewById(R.id.id_btn_registrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registrarUsuario(edtNombre1.getText().toString(), edtNombre2.getText().toString(), edtApellido1.getText().toString(),
                        edtApellido2.getText().toString(), edtTelefono.getText().toString(), edtCorreo.getText().toString(),
                        edtContrasena1.getText().toString(), edtContrasena2.getText().toString());
            }
        });

    }

    @Override
    public void showErrorRegistrar(String msj) {
        Snackbar.make(btnRegistrar, msj, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showTilErrorNombre(String msj) {
        tilNombre.setError(msj);
    }

    @Override
    public void showTilErrorApellido(String msj) {
        tilApellido.setError(msj);
    }

    @Override
    public void showTilErrorCorreo(String msj) {
        tilCorreo.setError(msj);
    }

    @Override
    public void showTilErrorContrasena1(String msj) {
        tilContrasena1.setError(msj);
    }

    @Override
    public void showTilErrorContrasena2(String msj) {
        tilContrasena2.setError(msj);
    }

    @Override
    public void onUsuarioRegistrado() {
        Toast.makeText(this, "Usuario registrado con éxito.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
