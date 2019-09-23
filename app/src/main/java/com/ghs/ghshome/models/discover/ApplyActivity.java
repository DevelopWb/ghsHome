package com.ghs.ghshome.models.discover;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.CommunityParticularsBean;
import com.ghs.ghshome.custom.MineEditCustomView;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.StrUtils;

/**
 * 报名提交
 */

public class ApplyActivity extends BaseActivity<DiscoverContract.IDiscoverView, DiscoverPresenter> implements View.OnClickListener, DiscoverContract.IDiscoverView {


    private MineEditCustomView entry_time;
    private MineEditCustomView mEntryName;
    private MineEditCustomView mEntryPhone;
    private MineEditCustomView mEntryContacts;
    private MineEditCustomView mEntryContactsPhone;
    private MineEditCustomView mEntrySite;

    /**
     * 确定
     */
    private TextView mEntryConfirm;
    private int community_apply_id;
    private CommunityParticularsBean.DataBean dataBean;
    private EditText entry_leaveMessage;
    private TextView entry_leaveMessage_hint;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_entry);
        initView();
    }

    private void initView() {

        mEntryName = (MineEditCustomView) findViewById(R.id.entry_name);
        mEntryName.setOnClickListener(this);
        mEntryPhone = (MineEditCustomView) findViewById(R.id.entry_phone);
        mEntryPhone.setOnClickListener(this);
        mEntryContacts = (MineEditCustomView) findViewById(R.id.entry_contacts);
        mEntryContacts.setOnClickListener(this);
        mEntryContactsPhone = (MineEditCustomView) findViewById(R.id.entry_contacts_phone);
        mEntryContactsPhone.setOnClickListener(this);
        mEntrySite = (MineEditCustomView) findViewById(R.id.entry_site);
        mEntrySite.setOnClickListener(this);
        mEntryConfirm = (TextView) findViewById(R.id.entry_confirm);
        mEntryConfirm.setOnClickListener(this);
        entry_leaveMessage = findViewById(R.id.entry_leaveMessage);
        entry_leaveMessage_hint = findViewById(R.id.entry_leaveMessage_hint);
    }

    @Override
    public void initLayoutView() {
        initActionBar("报名", "");
    }

    @Override
    public DiscoverPresenter creatPresenter() {
        return new DiscoverPresenter();
    }


    @Override
    public void getDate() {

        Intent intent = getIntent();
        dataBean = (CommunityParticularsBean.DataBean) intent.getSerializableExtra("COMMUNITY_APPLY");
        if (dataBean != null) {
            //报名人
            mEntryName.gettitleBarRightTv().setText(mUserInfoUtil.getFullName());
            //手机号
            mEntryPhone.gettitleBarRightTv().setText(mUserInfoUtil.getMobile());
            //联系人
            mEntryContacts.gettitleBarRightTv().setText(StrUtils.isStringValueOk(dataBean.getContactUser()) ? dataBean.getContactUser() : "暂无");
            //联系人电话
            mEntryContactsPhone.gettitleBarRightTv().setText(StrUtils.isStringValueOk(dataBean.getContactMobile()) ? dataBean.getContactMobile() : "暂无");
            //地点
            mEntrySite.gettitleBarRightTv().setText(dataBean.getAddress());

        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.entry_name:
                break;
            case R.id.entry_phone:
                break;
            case R.id.entry_contacts:
                break;
            case R.id.entry_contacts_phone:
                break;
            case R.id.entry_site:
                break;

            case R.id.entry_confirm:
                if (!StrUtils.isStringValueOk(entry_leaveMessage.getText().toString().trim())) {
                    entry_leaveMessage_hint.setText("*留言未填写");
                    entry_leaveMessage_hint.setTextColor(getResources().getColor(R.color.text_red));
                    return;
                } else {

                    entry_leaveMessage_hint.setText("留言信息");
                    entry_leaveMessage_hint.setTextColor(getResources().getColor(R.color.app_black));
                    getPresenter().getCommUnityApply(dataBean.getId(), mUserInfoUtil.getUserId(), mUserInfoUtil.getRoomId(), entry_leaveMessage.getText().toString().trim(), DiscoverContract.AC_ENTRY);

                }


                break;
        }
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        CommunityParticularsBean communityParticularsBean = (CommunityParticularsBean) o;
        if (communityParticularsBean != null) {
            showToast("报名" + communityParticularsBean.getMessage());
            setResult(ActivityResultManager.COMMUNITY_APLY);
            finish();
        }


    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }
}
