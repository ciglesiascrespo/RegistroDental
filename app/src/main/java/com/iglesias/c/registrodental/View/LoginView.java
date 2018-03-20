package com.iglesias.c.registrodental.View;

import android.content.Context;

/**
 * Created by Ciglesias on 17/02/2018.
 */

public interface LoginView {
    void showLoading();
    void hideLoading();
    void goToMainActivity();
    void showErrorLoginDialog();
    Context getContext();
    void showTilErrorUser(String msj);
    void showTilErrorPass(String msj);
}
