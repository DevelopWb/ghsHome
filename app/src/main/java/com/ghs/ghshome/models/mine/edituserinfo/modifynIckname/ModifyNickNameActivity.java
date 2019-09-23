package com.ghs.ghshome.models.mine.edituserinfo.modifynIckname;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.LoginBean;
import com.ghs.ghshome.models.mine.edituserinfo.EditUserInfoContract;
import com.ghs.ghshome.models.mine.edituserinfo.EditUserInfoModel;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/5/31 18:26.
 * application   修改昵称
 */
public class ModifyNickNameActivity extends BaseActivity implements RequestStatus, View.OnClickListener {

    private EditText mModifyNickNameTv;
    private String original_nickName;//原始昵称
    private String nowName;
    /**
     * 登录
     */
    private TextView mModifyNickConfirmTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_modify_nick_name);

    }


    /**
     * 保存修改
     */
    private void saveModify() {
        nowName = mModifyNickNameTv.getText().toString().trim();

        if (!checkUserNameFormat(nowName)) {
            return;
        }
        new EditUserInfoModel().updateUserInfo(UserInfoUtil.getInstance().getUserId(), getParamas("nickName", nowName), this, EditUserInfoContract.MODIFY_HEAD_IMAGE);
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    private Map<String, String> getParamas(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        return map;
    }


    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {
        mModifyNickNameTv = (EditText) findViewById(R.id.modify_nick_name_tv);
        initActionBar("更改名字", "",R.color.app_white);
        Intent intent = getIntent();
        original_nickName = intent.getStringExtra(ActivityResultManager.NICK_NAME);
        mModifyNickNameTv.setText(original_nickName);
        PubUtil.showSoftInputFromWindow(this, mModifyNickNameTv);
        mModifyNickNameTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                checkUserNameFormat(text);
            }
        });
        mModifyNickConfirmTv = (TextView) findViewById(R.id.modify_nick_confirm_tv);
        mModifyNickConfirmTv.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        nowName = mModifyNickNameTv.getText().toString().trim();
        if (nowName.equals(original_nickName)) {
            Intent intent = new Intent();
            intent.putExtra(ActivityResultManager.NICK_NAME, nowName);
            setResult(ActivityResultManager.MODIFY_NICK_NAME, intent);
            super.onBackPressed();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("是否保存更改")
                    .setNegativeButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            saveModify();

                        }
                    })
                    .setPositiveButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    }).show();
            setAlertDialogHeightWidth(alertDialog, -1, 0);

        }


    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        LoginBean loginBean = (LoginBean) o;
        Hawk.put(HawkProperty.LOGIN_BEAN, loginBean);
        original_nickName = nowName;
        onBackPressed();
    }

    @Override
    public void onError(String tag) {
        showToast(tag);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.modify_nick_confirm_tv:
                saveModify();
                break;
        }
    }
}
