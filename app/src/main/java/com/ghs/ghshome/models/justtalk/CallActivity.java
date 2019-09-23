package com.ghs.ghshome.models.justtalk;

import android.content.Context;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.bean.BluetoothsBean;
import com.ghs.ghshome.models.justtalk.jcevent.JCEvent;
import com.ghs.ghshome.models.main.MainModel;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.juphoon.cloud.JCCall;
import com.juphoon.cloud.JCCallItem;
import com.juphoon.cloud.JCMediaDevice;
import com.juphoon.cloud.JCMediaDeviceVideoCanvas;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.javac.ManyBlue.manager.EventManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


 /**
  * created by 海南
  * created date 2019/9/6 18:21.
  * application   视频通话
  */
public class CallActivity extends BaseActivity implements View.OnClickListener {

    private JCMediaDeviceVideoCanvas remoteCanvas;
    /**
     * 褐石公园一期1号楼1单元
     */
    private TextView mVideoName;
    private LinearLayout mVideoViewGroupLl;
    /**
     * 访客向您申请开门申请
     */
    private View video_answer;
    private View video_handUp;
    private View video_opendao;
    private View video_handup2;
    private JCCall jcCall;
    private JCCallItem jcCallItem;
    private boolean isAgreeAll;
    private Disposable disposable;
    private Disposable disposableCallIn;

    /**
     * 60秒后自动挂断,当前00:00
     */
    private TextView mHandUpNoticeTv;
    private JCMediaDeviceVideoCanvas remoteCanvas1;
    private JCManager mJcManager;
     private SurfaceView surfaceView;

     @Override
    public void getDate() {
        //申请权限
        requestPermission();


    }

    //调节音量大小
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            int direction = keyCode == KeyEvent.KEYCODE_VOLUME_UP ? AudioManager.ADJUST_RAISE : AudioManager.ADJUST_LOWER;
            int flags = AudioManager.FX_FOCUS_NAVIGATION_UP;
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            am.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL, direction, flags);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.video_answer_layout);
    }


    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    @Subscribe
    public void onEvent(JCEvent event) {
        if (event.getEventType() == JCEvent.EventType.LOGOUT) {
            //登录
            Log.d("TAG", "logout" + event.getEventType());

        } else if (event.getEventType() == JCEvent.EventType.LOGIN) {
            //登录
            Log.d("TAG", "login" + event.getEventType());
        } else if (event.getEventType() == JCEvent.EventType.CLIENT_STATE_CHANGE) {
            //改变
            Log.d("TAG", "改变" + event.getEventType());
        } else if (event.getEventType() == JCEvent.EventType.CALL_REMOVE) {
            Log.d("TAG", "页面移除" + event.getEventType());
            if (remoteCanvas1 != null) { // 远端视频销毁
                mVideoViewGroupLl.removeView(surfaceView);
                surfaceView=null;
                mJcManager.mediaDevice.stopVideo(remoteCanvas1);
                remoteCanvas1 = null;
            }
            onBackPressed();
            //页面移除
        } else if (event.getEventType() == JCEvent.EventType.CALL_UPDATE) {
            Log.d("TAG", "接入视频" + event.getEventType());
            //逻辑处理
            JCCallItem f = JCCallUtils.getActiveCall();
            if (jcCallItem.getState() == 3 && jcCallItem.getUploadVideoStreamOther()) {
                Log.d("TAG", "视频接听状态也" + jcCallItem.getState());
                // 获取远端视图画面，renderId来源JCCallItem对象
                remoteCanvas1 = mJcManager.mediaDevice.startVideo(jcCallItem.getRenderId(), JCMediaDevice.RENDER_FULL_SCREEN);
                surfaceView = remoteCanvas1.getVideoView();
                surfaceView.setZOrderOnTop(true);
                surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                mVideoViewGroupLl.addView(surfaceView);
                if (disposableCallIn == null) {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposableCallIn = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                            .take(60)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    String time = String.valueOf(aLong);
                                    time = time.length() == 1 ? "0" + time : time;
                                    Log.d(TAG, "接听中......" + time);
                                    if (aLong > 55) {
                                        mHandUpNoticeTv.setText("正在自动断开...");
                                    } else {
                                        mHandUpNoticeTv.setText("60秒后自动挂断,当前00:" + time);
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


        } else if (event.getEventType() == JCEvent.EventType.CALL_UI) {
            Log.d(TAG, "CALL_UI" + jcCallItem.getState());

        } else if (event.getEventType() == JCEvent.EventType.Exit) {
        }
    }

    private void requestPermission() {
        isAgreeAll = false;
        checkAppPermissions(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                isAgreeAll = true;
                //打开摄像头
                mJcManager.mediaDevice.startCamera();
                //打开语音
                mJcManager.mediaDevice.startAudio();
                setName();
            }

            @Override
            public void selectedAllPermission() {
                if (!isAgreeAll) {
                    requestPermission();
                }
            }
        }, R.string.perm_call_activity, PubUtil.promissions[1], PubUtil.promissions[3], PubUtil.promissions[4]);
    }


    private void initView() {
        mVideoName = (TextView) findViewById(R.id.video_name);
        mVideoViewGroupLl = (LinearLayout) findViewById(R.id.video_view_ll);
        //接电话
        video_answer = findViewById(R.id.video_answer);
        video_answer.setOnClickListener(this);
        //挂电话
        video_handUp = findViewById(R.id.video_handUp);
        video_handUp.setOnClickListener(this);
        //开门
        video_opendao = findViewById(R.id.video_opendao);
        video_opendao.setOnClickListener(this);
        //挂断2
        video_handup2 = findViewById(R.id.video_handup2);
        video_handup2.setOnClickListener(this);

        mJcManager = JCManager.getInstance();
        jcCall = mJcManager.call;
        jcCallItem = JCCallUtils.getActiveCall();

        //30后挂断
        disposable = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .take(30)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(TAG, "未接听......" + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        jcCall.term(jcCallItem, 0, "未接通");
                    }
                });

        mHandUpNoticeTv = (TextView) findViewById(R.id.handUp_notice_tv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.video_handUp:
                //挂断
                handUpVideo();
                break;
            case R.id.video_opendao:
                EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_RELEASE));
                MainModel mainModel = new MainModel();
                int deviceid = Integer.parseInt(jcCallItem.getExtraParam());
                showProgressDialog();
                mainModel.openDoorByNet(deviceid, -1, new RequestStatus() {
                    @Override
                    public void onStart(String tag) {

                    }

                    @Override
                    public void onSuccess(Object o, String tag) {
                        if ("1000".equals(o.toString())) {
                            showToast("开门成功");
                            stopProgressDialog();
                            //添加积分
//                                execSeedTask(SeedTaskAdapter.SEED_TASK_OPEN_BY_MOBILE,-1);
                        }
                    }

                    @Override
                    public void onError(String tag) {
                        showToast("开门失败");
                        stopProgressDialog();

                    }
                });


                break;
            case R.id.video_handup2:
                //接通视频后的挂断
                handUpVideo();
                break;
            case R.id.video_answer:
                EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_RELEASE));
                setViewsVisible(mVideoViewGroupLl, mHandUpNoticeTv, video_handup2);
                setViewsInvisible(true, mVideoName, video_handUp, video_answer);
                jcCall.answer(jcCallItem, true);
                break;
        }
    }

    /**
     * 挂断电话
     */
    private void handUpVideo() {
        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_RELEASE));
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        try {
            jcCall.term(jcCallItem, 0, "挂断");
            mJcManager.mediaDevice.stopCamera();
            mJcManager.mediaDevice.stopAudio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        overridePendingTransition(R.anim.activity_stop, R.anim.activity_out);
//        try {
//            jcCall.term(jcCallItem, 0, "切换回主界面");
//            mJcManager.mediaDevice.stopCamera();
//            mJcManager.mediaDevice.stopAudio();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //设置来电名称
    public void setName() {
        mVideoName.setVisibility(View.VISIBLE);
        String daoName = null;
        BluetoothsBean beana = Hawk.get(HawkProperty.BLUE_ADDRS);
        if (beana != null) {
            List<BluetoothsBean.DataBean> bean = beana.getData();
            String extraParam = jcCallItem.getExtraParam();
            if (extraParam != null) {
                int sid = Integer.parseInt(extraParam);
                if (bean.size() != 0) {
                    for (int i = 0; i < bean.size(); i++) {
                        if (bean.get(i).getId() == sid) {
                            int deviceType = bean.get(i).getDeviceType();
                            switch (deviceType) {
                                case 1:
                                    //大门
                                    daoName = UserInfoUtil.getInstance().getVillageName() + "-" + bean.get(i).getDeviceName();
                                    break;

                                case 2:
                                    //单元门

                                    daoName = UserInfoUtil.getInstance().getVillageName() + "-"
                                            + UserInfoUtil.getInstance().getCellname() +
                                            "-" + UserInfoUtil.getInstance().getPowerName()
                                            + "-" + bean.get(i).getDeviceName();
                                    break;

                            }

                            break;
                        }
                    }


                }
                daoName = daoName == null ? "其他单元访客来电" : daoName;
                mVideoName.setText(daoName);


            }

        }


    }


    /**
     * 判断小区id是否相同
     *
     * @return
     */
    public boolean isVillageid() {
        boolean flag = false;
        BluetoothsBean beana = Hawk.get(HawkProperty.BLUE_ADDRS);
        if (beana != null) {
            List<BluetoothsBean.DataBean> bean = beana.getData();
            String extraParam = jcCallItem.getExtraParam();
            if (extraParam != null) {
                int sid = Integer.parseInt(extraParam);
                if (bean.size() != 0) {
                    for (int i = 0; i < bean.size(); i++) {

                        if (bean.get(i).getId() == sid) {
                            flag = true;
                            break;
                        }

                    }

                }


            }

        }
        return flag;

    }

    @Override
    public void onRefuseGivePromission() {
        handUpVideo();
        super.onRefuseGivePromission();
    }


}
