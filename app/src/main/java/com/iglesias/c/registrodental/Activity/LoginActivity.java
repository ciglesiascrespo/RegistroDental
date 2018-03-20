package com.iglesias.c.registrodental.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iglesias.c.registrodental.Db.DbHandler;
import com.iglesias.c.registrodental.Presenter.LoginPresenterImpl;
import com.iglesias.c.registrodental.R;
import com.iglesias.c.registrodental.View.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    TextInputLayout tilUser;
    TextInputLayout tilPass;
    TextInputEditText edtUser;
    TextInputEditText edtPass;
    Button btnLogin;
    TextView txtRegistrase;

    private LoginPresenterImpl presenter;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DbHandler.getInstance(this);
        presenter = new LoginPresenterImpl(this);
        setupViews();
        setupLoading();
        presenter.verificarUsuarioLogueado();
    }

    private void setupViews() {

        tilPass = findViewById(R.id.id_til_login_pass);
        tilUser = findViewById(R.id.id_til_login_user);
        edtPass = findViewById(R.id.id_edt_login_pass);
        edtUser = findViewById(R.id.id_edt_login_user);
        btnLogin = findViewById(R.id.id_btn_login_ingresar);

        txtRegistrase = findViewById(R.id.id_txt_login_registrarse);

        txtRegistrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickBtnLogin(edtUser.getText().toString(), edtPass.getText().toString());
            }
        });

    }

    private void setupLoading() {
        loading = new ProgressDialog(this);
        loading.setCancelable(false);
        loading.setMessage(getResources().getString(R.string.str_loading_login));
    }

    @Override
    public void showLoading() {
        if (!loading.isShowing()) loading.show();
    }

    @Override
    public void hideLoading() {
        if (loading.isShowing()) loading.dismiss();
    }

    @Override
    public void goToMainActivity() {

        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void showErrorLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.myDialog);

        builder.setTitle(getResources().getString(R.string.str_title_alert_error_login));
        builder.setMessage(getResources().getString(R.string.str_msj_alert_error_login));
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showTilErrorUser(String msj) {
        tilUser.setError(msj);
    }

    @Override
    public void showTilErrorPass(String msj) {
        tilPass.setError(msj);
    }
}
