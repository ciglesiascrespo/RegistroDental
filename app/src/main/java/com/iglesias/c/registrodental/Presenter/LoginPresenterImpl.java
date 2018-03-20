package com.iglesias.c.registrodental.Presenter;

import com.iglesias.c.registrodental.Iterator.LoginIterator;
import com.iglesias.c.registrodental.R;
import com.iglesias.c.registrodental.View.LoginView;


/**
 * Created by Ciglesias on 17/02/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {

    LoginView view;
    LoginIterator iterator;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        iterator = new LoginIterator(view.getContext(), this);

    }


    public void onClickBtnLogin(String user, String pass) {
        if (user.length() > 0 && pass.length() > 0) {
            view.showLoading();
            iterator.login(user, pass);
        } else {
            view.showTilErrorPass(pass.length() > 0 ? null : view.getContext().getResources().getString(R.string.str_campo_requerido));
            view.showTilErrorUser(user.length() > 0 ? null : view.getContext().getResources().getString(R.string.str_campo_requerido));
        }
    }

    @Override
    public void onSuccesLogin() {
        view.hideLoading();
        view.goToMainActivity();
    }

    @Override
    public void onErrorLogin() {
        view.hideLoading();
        view.showErrorLoginDialog();
    }


    public void verificarUsuarioLogueado() {
        iterator.verificarUsuarioLogueado();
    }


    @Override
    public void onUsuarioLogueado() {
        view.goToMainActivity();
    }
}
