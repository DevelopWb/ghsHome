package com.ghs.ghshome.models.homePage.keymanager.addkey;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * created by tobato
 * created date 2019/7/29 19:12.
 * application  添加授权
 */
public class AddkeyActivity extends BaseActivity<AddKeyContract.AddKey_if_View, AddKeyPresent> implements AddKeyContract.AddKey_if_View, View.OnClickListener {

    /**
     * 褐石公园1期3-1-5
     */
    private TextView mAllotKeyUserAddrTv;

    /**
     * -
     */
    private ImageView mAllotKeyRemoveTv;
    /**
     * 1
     */
    private TextView mAllotKeyAmountTv;
    /**
     * +
     */
    private ImageView mAllotKeyAddTv;
    private LinearLayout mAllotKeyGuestLl;
    private EditText mAllotKeyNameEt;
    private EditText mAllotKeyPhoneEt;
    /**
     * 确认
     */
    private TextView mAllotKeyConfirmTv;
    private boolean roleType = true;//true代表家人，false代表房客
    private LinearLayout add_key_ly;
    private List<String> strings;
    /**
     * 请选择
     */
    private TextView mAddKeySf;
    private LinearLayout mAddKeySfLy;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_addkey);
    }

    @Override
    public void initLayoutView() {

        initView();
        initActionBar("添加授权", null);

    }

    @Override
    public AddKeyPresent creatPresenter() {

        return new AddKeyPresent();
    }

    @Override
    public void getDate() {


    }

    private void initView() {
        strings = new ArrayList<>();
        mAllotKeyUserAddrTv = (TextView) findViewById(R.id.allot_key_user_addr_tv);
        mAllotKeyUserAddrTv.setText(UserInfoUtil.getInstance().getVillageMsg());
        mAllotKeyRemoveTv = (ImageView) findViewById(R.id.allot_key_remove_tv);
        mAllotKeyRemoveTv.setOnClickListener(this);
        mAllotKeyAmountTv = (TextView) findViewById(R.id.allot_key_amount_tv);
        mAllotKeyAddTv = (ImageView) findViewById(R.id.allot_key_add_tv);
        mAllotKeyAddTv.setOnClickListener(this);


        mAllotKeyNameEt = (EditText) findViewById(R.id.allot_key_name_et);
        mAllotKeyNameEt.setOnClickListener(this);
        mAllotKeyPhoneEt = (EditText) findViewById(R.id.allot_key_phone_et);
        mAllotKeyPhoneEt.setOnClickListener(this);
        mAllotKeyConfirmTv = (TextView) findViewById(R.id.allot_key_confirm_tv);
        mAllotKeyConfirmTv.setOnClickListener(this);
        add_key_ly = findViewById(R.id.add_key_ly);
        mAddKeySf = (TextView) findViewById(R.id.add_key_sf);
        mAddKeySfLy = (LinearLayout) findViewById(R.id.add_key_sf_ly);
        mAddKeySfLy.setOnClickListener(this);
        //	角色 1业主 2业主家人 3租客 4租客家人
        if (UserInfoUtil.getInstance().getRoleType() == 3) {//租户只能添加家人
            strings.add("租客家人");
            mAddKeySf.setText("租客家人");
            selectAddedRole(0);
            add_key_ly.setVisibility(View.GONE);

        } else {
            if (UserInfoUtil.getInstance().getRoleType() == 1) {//业主
                strings.add("业主家人");
                strings.add("租客");

            }

        }

    }

    @Override
    public void checkFormatSuccess() {

    }

    @Override
    public void checkFormatError(String error) {
        showToast(error);
    }

    @Override
    public void startLoading(String tag) {
        showProgressDialog();
    }

    @Override
    public void stopLoading(String tag) {
    }

    @Override
    public void updateView(Object o, String tag) {
        String msg = (String) o;
        if ("成功".equals(msg)) {
            setResult(ActivityResultManager.ADD_KEY_RESULT);
            finish();
        } else {
            showToast(msg);
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }


    @Override
    public void onClick(View v) {
        int key_amount_int = -1;
        switch (v.getId()) {
            default:
                break;
            case R.id.allot_key_confirm_tv:
                int role = -1;
                int key_amount = -1;
                int user_role = UserInfoUtil.getInstance().getRoleType();
                if (1 == user_role) {//业主
                    //true 业主 false 租户
                    if (roleType) {
                        role = 2;
                    } else {
                        role = 3;
                    }
                } else {
                    if (roleType) {
                        role = 4;
                    } else {
                        role = 3;
                    }
                }
                if (role == 2 || role == 4) {
                    key_amount = 1;
                } else {
                    key_amount = Integer.parseInt(mAllotKeyAmountTv.getText().toString().trim());
                }
                String name = mAllotKeyNameEt.getText().toString().trim();
                if (!checkUserNameFormat(name)) {
                    return;
                }
                showProgressDialog();
                    Log.i("TAG", role + "-------" + key_amount + "------" + mAllotKeyPhoneEt.getText().toString().trim() + "------" + name);
                    getPresenter().checkFormat(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getRoomId(), role, key_amount, mAllotKeyPhoneEt.getText().toString().trim(), name);
                break;

            case R.id.allot_key_remove_tv:
                selectAddedRole(1);
                key_amount_int = Integer.parseInt(mAllotKeyAmountTv.getText().toString().trim());
                if (key_amount_int > 1) {
                    key_amount_int--;
                }
                mAllotKeyAmountTv.setText(String.valueOf(key_amount_int));
                break;
            case R.id.allot_key_add_tv:
                selectAddedRole(1);
                key_amount_int = Integer.parseInt(mAllotKeyAmountTv.getText().toString().trim());
                key_amount_int++;
                if (key_amount_int > PubUtil.LEFT_KEY_NUM - 1) {
                    showToast("超出总钥匙数量");

                    return;
                }
                mAllotKeyAmountTv.setText(String.valueOf(key_amount_int));
                break;

            case R.id.allot_key_name_et:

                break;

            case R.id.allot_key_phone_et:


                break;

            case R.id.add_key_sf_ly:
                hideKeyboard(v);
                if (strings.size()>0) {
                    //选择身份
                    PickerManager.getInstance().showOptionPicker(this, strings,new PickerManager.OnOptionPickerSelectedListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            mAddKeySf.setText(strings.get(options1));
                            selectKeyView(strings, options1);
                        }
                    });
                }


                break;
        }
    }

    /**
     * 选择需要添加的住户角色
     * <p>
     * tag  //0代表家人 1代表租户
     */
    private void selectAddedRole(int tag) {

        switch (tag) {
            case 0:
                roleType = true;
                break;
            case 1:
                roleType = false;
                break;
            default:
                break;
        }
    }

    /**
     * @param list     数据集合
     * @param options1 当前prostion
     */
    public void selectKeyView(List<String> list, int options1) {

        if ("业主家人".equals(list.get(options1))) {
            selectAddedRole(0);
            add_key_ly.setVisibility(View.GONE);

        } else if ("租客".equals(list.get(options1))) {
            selectAddedRole(1);
            add_key_ly.setVisibility(View.VISIBLE);
        } else if ("租客家人".equals(list.get(options1))) {
            selectAddedRole(0);
            add_key_ly.setVisibility(View.GONE);
        }
    }

}
