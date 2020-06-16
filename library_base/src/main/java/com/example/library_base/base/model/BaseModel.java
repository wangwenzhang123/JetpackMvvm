package com.example.library_base.base.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.library_base.base.view.BaseModelView;
import com.example.library_base.net.bean.ExBaseEntity;
import com.example.library_base.net.transformer.KResponseTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseModel extends ViewModel implements BaseModelView {
    private CompositeDisposable mCompositeDisposable;
    public MutableLiveData<Boolean> isShowLoadingData=new MutableLiveData<>();
    @Override
    public void addDisposable(Disposable subscription) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public <T extends ExBaseEntity> ObservableTransformer<T, T> handleEverythingResult() {
        return handleEverythingResult(true);
    }

    @Override
    public <T extends ExBaseEntity> ObservableTransformer<T, T> handleEverythingResult(final String loadingMessage) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return handleCommon(upstream, true, loadingMessage)
                        .flatMap(new KResponseTransformer.ResponseFunction<T>());
            }
        };
    }

    @Override
    public <T extends ExBaseEntity> ObservableTransformer<T, T> handleEverythingResult(final boolean showLoading) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return handleCommon(upstream, showLoading, null)
                        .flatMap(new KResponseTransformer.ResponseFunction<T>());
            }
        };
    }

    @Override
    public <T extends ExBaseEntity> ObservableTransformer<T, T> handleOnlyNetworkResult() {
        return handleOnlyNetworkResult(true);
    }

    @Override
    public <T extends ExBaseEntity> ObservableTransformer<T, T> handleOnlyNetworkResult(final String loadingMessage) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return handleCommon(upstream, true, loadingMessage);
            }
        };
    }

    @Override
    public <T extends ExBaseEntity> ObservableTransformer<T, T> handleOnlyNetworkResult(final boolean showLoading) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return handleCommon(upstream, showLoading, null);
            }
        };
    }

    private <T extends ExBaseEntity> Observable<T> handleCommon(Observable<T> upstream, final boolean showLoading, final String loadingMessage) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new KResponseTransformer.ErrorResumeFunction<T>())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (showLoading) {
                            isShowLoadingData.setValue(true);
                        }
                        addDisposable(disposable);
                    }
                })
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (showLoading) {
                            isShowLoadingData.setValue(false);
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if ( showLoading) {
                            isShowLoadingData.setValue(false);
                        }
                    }
                });
    }

}
