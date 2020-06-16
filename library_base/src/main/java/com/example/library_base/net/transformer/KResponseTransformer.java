package com.example.library_base.net.transformer;



import com.example.library_base.net.bean.ExBaseEntity;
import com.example.library_base.net.exception.KExceptionHandler;
import com.example.library_base.net.exception.KResultException;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * description：异常的统一处理
 * <p>
 * author： DS.Hu
 * <p>
 * time： 2018/7/23 18:58
 * <p>
 */
public class KResponseTransformer {

    public static <T extends ExBaseEntity> ObservableTransformer<T, T> handleResult() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(new ErrorResumeFunction<T>())
                        .flatMap(new ResponseFunction<T>());
            }
        };
    }


    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    public static class ErrorResumeFunction<T extends ExBaseEntity> implements Function<Throwable, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Throwable throwable) throws Exception {
            return Observable.error(KExceptionHandler.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    public static class ResponseFunction<T extends ExBaseEntity> implements Function<T, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(T entity) throws Exception {
            if (entity.isSuccessful() || entity.isExpireLogin()) {
                return Observable.just(entity);
            } else {
                return Observable.error(new KResultException(entity.getCode(), entity.getMessage()));
            }
        }
    }


}
