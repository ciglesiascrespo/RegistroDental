package com.iglesias.c.registrodental.Iterator;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.iglesias.c.registrodental.Db.DbHandler;
import com.iglesias.c.registrodental.Pojo.Usuario;
import com.iglesias.c.registrodental.Presenter.LoginPresenter;
import com.iglesias.c.registrodental.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 17/02/2018.
 */

public class LoginIterator {
    private final String TAG = getClass().getName();

    private Context context;
    private LoginPresenter presenter;
    private SharedPreferences preferences;
    private DbHandler dbHandler;

    public LoginIterator(Context context, LoginPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        preferences = context.getSharedPreferences(context.getResources().getString(R.string.name_preference_user), Context.MODE_PRIVATE);
        dbHandler = DbHandler.getInstance(context);
    }

    public void verificarUsuarioLogueado() {
        boolean result = false;
        int id = 0;


        if (preferences != null && preferences.getBoolean(context.getResources().getString(R.string.name_preference_user_logueado), false)) {
            result = true;
            id = preferences.getInt(context.getResources().getString(R.string.name_preference_user_codigo), 0);
        }
        if (result) presenter.onUsuarioLogueado();


    }

    public void login(final String user, final String pass) {
        Observable<Usuario> observable = Observable.create(new Observable.OnSubscribe<Usuario>() {
            @Override
            public void call(Subscriber<? super Usuario> subscriber) {
                Usuario usuario = dbHandler.verificaUsuario(user, pass);
                subscriber.onNext(usuario);
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());

        observable.subscribe(new Subscriber<Usuario>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                presenter.onErrorLogin();
            }

            @Override
            public void onNext(Usuario result) {
                if (result.getIdUsuario() > 0) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(context.getResources().getString(R.string.name_preference_user_logueado), true);
                    editor.putInt(context.getResources().getString(R.string.name_preference_user_codigo), result.getIdUsuario());
                    editor.apply();
                    presenter.onSuccesLogin();

                } else {
                    presenter.onErrorLogin();
                }
            }
        });


    }
}
