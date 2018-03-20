package com.iglesias.c.registrodental.Iterator;

import android.content.Context;
import android.content.SharedPreferences;

import com.iglesias.c.registrodental.Db.DbHandler;
import com.iglesias.c.registrodental.Pojo.Usuario;
import com.iglesias.c.registrodental.Presenter.RegistrarPresenter;
import com.iglesias.c.registrodental.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 18/03/2018.
 */

public class RegistrarIterator {
    RegistrarPresenter presenter;
    private DbHandler dbHandler;

    public RegistrarIterator(RegistrarPresenter presenter, Context context) {
        this.presenter = presenter;
        dbHandler = DbHandler.getInstance(context);
    }

    public void registrarUsuario(final Usuario usuario) {

        Observable<Boolean> observable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(dbHandler.verificaUsuarioExistente(usuario.getCorreo()));
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());

        observable.subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Boolean result) {
                if (result) {
                    presenter.onUsuarioExiste();
                } else {
                    Observable.create(new Observable.OnSubscribe<Boolean>() {
                        @Override
                        public void call(Subscriber<? super Boolean> subscriber) {
                            subscriber.onNext(dbHandler.registrarUsuario(usuario));
                            subscriber.onCompleted();
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<Boolean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            presenter.onError();
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                presenter.onUsuarioRegistrado();
                            } else {
                                presenter.onError();
                            }
                        }
                    });
                }
            }
        });

    }
}
