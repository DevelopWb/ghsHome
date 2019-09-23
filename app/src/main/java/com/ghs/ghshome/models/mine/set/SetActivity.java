package com.ghs.ghshome.models.mine.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.custom.MineEditCustomView;
import com.ghs.ghshome.models.justtalk.JCManager;
import com.ghs.ghshome.models.push.AliPushManager;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.FileUtil;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.orhanobut.hawk.Hawk;

import io.javac.ManyBlue.manager.EventManager;

/**
 * created by tobato
 * created date 2019/5/31 18:22.
 * application   设置
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {

    private MineEditCustomView mSetUserProtocalMcv;
    private MineEditCustomView mSetUserClearCacheMcv;
    private MineEditCustomView mSetUserQuiteAppMcv;
    private BottomSheetDialog bottomSheetDialog;
    private LinearLayout mSetUserProtocalLl;
    private LinearLayout mSetUserClearCacheLl;
    private LinearLayout mSetUserQuiteAppLl;
    private MineEditCustomView mSetUserVersionMcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void getDate() {
        initCacheTextValue();

    }

    /**
     * 初始化缓存的大小
     */
    private void initCacheTextValue() {
        String size = null;
        try {
            size = FileUtil.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"0B".equals(size)) {
            mSetUserClearCacheMcv.gettitleBarRightTv().setText(size);
        } else {
            mSetUserClearCacheMcv.gettitleBarRightTv().setText("");
        }
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("设置", "");

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_set);

    }


    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {
        mSetUserProtocalMcv = (MineEditCustomView) findViewById(R.id.set_user_protocal_mcv);
        mSetUserClearCacheMcv = (MineEditCustomView) findViewById(R.id.set_user_clear_cache_mcv);
        mSetUserQuiteAppMcv = (MineEditCustomView) findViewById(R.id.set_user_quite_app_mcv);
        mSetUserProtocalLl = (LinearLayout) findViewById(R.id.set_user_protocal_ll);
        mSetUserProtocalLl.setOnClickListener(this);
        mSetUserClearCacheLl = (LinearLayout) findViewById(R.id.set_user_clear_cache_ll);
        mSetUserClearCacheLl.setOnClickListener(this);
        mSetUserQuiteAppLl = (LinearLayout) findViewById(R.id.set_user_quite_app_ll);
        mSetUserQuiteAppLl.setOnClickListener(this);
        mSetUserVersionMcv = (MineEditCustomView) findViewById(R.id.set_user_version_mcv);
        mSetUserVersionMcv.gettitleBarRightTv().setText(PubUtil.getAPPVersion(this));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mine_set_quit_app_tv:
                //退出登录

                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                PubUtil.LOGIN_ENTER = 2;

                //清除视频通话登录记录
                JCManager.getInstance().client.logout();

                //解绑小区id及标签
                AliPushManager.getInstance().unbindAll();
//                //解除小米推送
//                MiPushClient.unregisterPush(this);
                setResult(ActivityResultManager.MINE_SET_QUIT_APP);
                Hawk.delete(HawkProperty.LOGIN_BEAN);
                finish();
                break;
            case R.id.mine_set_exit_cancel_tv:
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }

                break;
            default:
                break;
            case R.id.set_user_protocal_ll:
                startActivity(new Intent(this, UserProtocalActivity.class));
                break;
            case R.id.set_user_clear_cache_ll:
                if (!TextUtils.isEmpty(mSetUserClearCacheMcv.gettitleBarRightTv().getText().toString().trim())) {
                    showProgressDialog();
                    new ClearCacheThread(new ClearCacheListener() {
                        @Override
                        public void onClearCacheFinished() {
                            stopProgressDialog();
                            showToast("清理完成");
                            initCacheTextValue();
                        }
                    }).run();
                }
                break;
            case R.id.set_user_quite_app_ll:
                if (!mUserInfoUtil.isLogin()) {
                    EventManager.sendStringMsg(PubUtil.EVENTBUS_TOCKEN_NO_ACTION);
                    finish();
                    return;
                }
                View view = LayoutInflater.from(this).inflate(R.layout.mine_set_quit_app, null);
                bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
                view.findViewById(R.id.mine_set_quit_app_tv).setOnClickListener(this);
                view.findViewById(R.id.mine_set_exit_cancel_tv).setOnClickListener(this);
                break;
        }
    }


    public class ClearCacheThread implements Runnable {
        private ClearCacheListener mClearCacheListener;

        public ClearCacheThread(ClearCacheListener mClearCacheListener) {
            this.mClearCacheListener = mClearCacheListener;
        }

        @Override
        public void run() {
            if (FileUtil.clearCacheMemory(SetActivity.this)) {
                if (mClearCacheListener != null) {
                    mClearCacheListener.onClearCacheFinished();
                }
            }
        }


    }

    public interface ClearCacheListener {
        void onClearCacheFinished();

    }

}
