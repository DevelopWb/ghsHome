package com.ghs.ghshome.tools;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:wang_sir
 * Time:2019/2/12 17:49
 * Description:This is RxScheduler
 */
public class RxScheduler {
    public static <T> void doOnIoThread(IOTask<T> ioTask) {
        Observable.just(ioTask).observeOn(Schedulers.io())
                .subscribe(new Consumer<IOTask<T>>() {
                    @Override
                    public void accept(IOTask<T> ioTask) throws Exception {
                        ioTask.doOnIOThread();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }


    public interface IOTask<T> {
        void doOnIOThread();
    }
}
