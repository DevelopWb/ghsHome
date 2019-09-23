package com.ghs.ghshome.models.propertyHall.openDoorByMobile;

import android.util.Log;

import com.ghs.ghshome.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:wang_sir
 * Time:2019/8/5 15:46
 * Description:This is MobileOpenTimer
 * 手机开门计时
 */
public class MobileOpenTimer {
    public static  boolean  startCountTime = true;//开始计时
    public static  int  clickOpendDoorNum = 0;//点击蓝牙开门的次数 30s内
    public static   long  waitTime = 0;//等待时间
    private static Disposable disposableBefore;
    private static  String TAG  = "MobileOpenTimer";
    /**
     * 开门计时逻辑
     */
    public  static  void openTimeEngin(OneKeyOpenDoor oneKeyOpenDoor, BaseActivity activity) {
        if (clickOpendDoorNum<6) {
            oneKeyOpenDoor.oneKeyOpenTheDoor();
        }else{
            waitTime =waitTime==0?30:waitTime;
            activity.showToast("您的操作过于频繁，请耐心等待"+waitTime+"秒");
        }
        //30s内不能重复点击超过5次
        if (startCountTime) {
            startCountTime = false;
            disposableBefore = Observable.interval(0,1000, TimeUnit.MILLISECONDS)
                    .take(30)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.d(TAG,"clickOpend---1----DoorNum"+clickOpendDoorNum+"+++++++"+aLong);
                            if (clickOpendDoorNum>5) {
                                //30s内 第6次点击  提示操作频繁 30秒后重试  开始计时30s
                                disposableBefore.dispose();
                                Observable.interval(0,1000, TimeUnit.MILLISECONDS)
                                        .take(30)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<Long>() {
                                            @Override
                                            public void accept(Long aLong) throws Exception {
                                                Log.d(TAG,"clickOpend----2----DoorNum"+clickOpendDoorNum+"+++++++"+aLong);
                                                waitTime = 29-aLong;
                                                if (29==aLong) {
                                                    waitTime = 0;
                                                    startCountTime = true;
                                                    clickOpendDoorNum = 0;
                                                }
                                            }
                                        });

                            }else{
                                //没有超过次数
                                if (29==aLong) {
                                    startCountTime = true;
                                    clickOpendDoorNum = 0;
                                }
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                        }
                    });
        }
    }


}
