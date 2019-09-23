package com.ghs.ghshome.models.checkIdentity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.bean.RoomsBean;
import com.ghs.ghshome.bean.UserAndRoomBean;
import com.ghs.ghshome.bean.VillageListBean;
import com.ghs.ghshome.custom.CustomActionBar;
import com.ghs.ghshome.models.checkIdentity.selectRoom.SelectRoomActivity;
import com.ghs.ghshome.models.main.MainActivity;
import com.ghs.ghshome.models.main.houseManager.HouseManagerActivity;
import com.ghs.ghshome.tools.ActivityManagerTool;
import com.ghs.ghshome.tools.IntentUtil;
import com.ghs.ghshome.tools.PickerManager;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/9/11 11:19.
 * application   身份验证
 */
public class CheckIdentityActivity extends BaseActivity<CheckIdentityContract.ICheckIdentityView, CheckIdentityPresent> implements CheckIdentityContract.ICheckIdentityView, View.OnClickListener {

    private CustomActionBar mMineCarCab;
    /**
     * 社区名称
     */
    private TextView mVillageNameTv;
    /**
     * 101
     */
    private TextView mUserRoomTv;
    private ConstraintLayout mUserRoomCl;
    /**
     * 业主
     */
    private TextView mUserIdTv;
    private ConstraintLayout mUserIdCl;
    /**
     * 1888888888
     */
    private TextView mUserMobileTv;
    /**
     * 请填写真实姓名
     */
    private EditText mUserNameEt;
    /**
     * 提交认证
     */
    private TextView mCommitCheckTv;
    private VillageListBean.DataBean dataBean;
    private RoomsBean.DataBean roomBean;
    private UserAndRoomBean.DataBean.GhsUserRoomDOBean roomDOBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_check_identity);
        initView();
    }

    @Override
    public CheckIdentityPresent creatPresenter() {
        return new CheckIdentityPresent();
    }
    @Override
    public void getDate() {
        getPresenter().getUserName(mUserInfoUtil.getMobile(), CheckIdentityContract.GET_USER_NAME);
        dataBean = (VillageListBean.DataBean) IntentUtil.getInstance().getIntentParcelableData(this);

        if (dataBean != null) {
            mVillageNameTv.setText(dataBean.getVillageName());
            mUserMobileTv.setText(mUserInfoUtil.getMobile());
        } else {
            roomDOBean = mUserInfoUtil.getCurrentVillageBean();
            if (roomDOBean != null) {
                mVillageNameTv.setText(roomDOBean.getVillageName());
                mUserMobileTv.setText(roomDOBean.getMobile());
                mUserRoomTv.setText(PubUtil.getRoomMsg(roomDOBean));
                mUserIdTv.setText(getRoleName(roomDOBean.getRoleType()));
            }
        }

    }

    /**
     * 获取角色角色 1业主 2业主家人 3租客 4租客家人 5其他
     *
     * @return
     */
    private String getRoleName(int roleType) {
        String roleName = "";
        switch (roleType) {
            case 1:
                roleName = "业主";
                break;
            case 2:
                roleName = "业主家人";
                break;
            case 3:
                roleName = "租客";
                break;
            case 4:
                roleName = "租客家人";
                break;
            case 5:
                roleName = "其他";
                break;
            default:
                break;
        }
        return roleName;
    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case CheckIdentityContract.GET_USER_NAME:
                String name = (String) o;
                if (StrUtils.isStringValueOk(name) && !"null".equals(name)) {
                    mUserNameEt.setClickable(false);
                    mUserNameEt.setFocusable(false);
                    mUserNameEt.setText(name);
                } else {
                    mUserNameEt.setClickable(true);
                    mUserNameEt.setFocusable(true);
                }
                break;
            case CheckIdentityContract.SUBMIT_CHECK:
                UserAndRoomBean.DataBean.GhsUserRoomDOBean roomDOBean = (UserAndRoomBean.DataBean.GhsUserRoomDOBean) o;
                if (0 == PubUtil.SUBMIT_CHECK_ENTRY) {
                    if (roomDOBean != null) {
                        mUserInfoUtil.saveCurrentVillageBean(roomDOBean);
                    }
                    ActivityManagerTool.getInstance().finishApp();
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    startActivity(new Intent(this, HouseManagerActivity.class));

                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    private void initView() {

        mVillageNameTv = (TextView) findViewById(R.id.village_name_tv);
        mUserRoomTv = (TextView) findViewById(R.id.user_room_tv);
        mUserRoomCl = (ConstraintLayout) findViewById(R.id.user_room_cl);
        mUserRoomCl.setOnClickListener(this);
        mUserIdTv = (TextView) findViewById(R.id.user_id_tv);
        mUserIdCl = (ConstraintLayout) findViewById(R.id.user_id_cl);
        mUserIdCl.setOnClickListener(this);
        mUserMobileTv = (TextView) findViewById(R.id.user_mobile_tv);
        mUserNameEt = (EditText) findViewById(R.id.user_name_et);
        mCommitCheckTv = (TextView) findViewById(R.id.commit_check_tv);
        mCommitCheckTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.user_room_cl:
                //选择房间
                    IntentUtil.getInstance().startActivityWithParcelableData(dataBean, this, SelectRoomActivity.class);
                break;
            case R.id.user_id_cl:
                //选择身份
                PickerManager.getInstance().showOptionPicker(this, getUserRoles(), new PickerManager.OnOptionPickerSelectedListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        mUserIdTv.setText(getUserRoles().get(options1));
                    }
                });
                break;
            case R.id.commit_check_tv:
                String roomName = mUserRoomTv.getText().toString().trim();
                if (!StrUtils.isStringValueOk(roomName)) {
                    showToast("请选择房屋号");
                    return;
                }
                String userCard = mUserIdTv.getText().toString().trim();
                if (!StrUtils.isStringValueOk(userCard)) {
                    showToast("请选择住户身份");
                    return;
                }
                String userName = mUserNameEt.getText().toString().trim();
                if (!StrUtils.isStringValueOk(userName)) {
                    showToast("请填写姓名");
                    return;
                }
                if (roomBean != null) {
                    getPresenter().submitCheck(mUserInfoUtil.getUserId(), roomBean.getId(), getRoleType(userCard), mUserInfoUtil.getMobile(), userName, CheckIdentityContract.SUBMIT_CHECK);
                }else{
                    getPresenter().reSubmitCheck(roomDOBean.getId(),roomDOBean.getRoomId(),getRoleType(userName), CheckIdentityContract.SUBMIT_CHECK);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == IntentUtil.getInstance().RESULT_CODE) {
            if (data != null) {
                roomBean = data.getParcelableExtra(IntentUtil.getInstance().INTENT_KEY);
                if (roomBean != null) {
                    mUserRoomTv.setText(roomBean.getPortionName() + "-" + roomBean.getTowerNumber() + "-" + roomBean.getCellName() + "-" + roomBean.getRoomNumber());
                }
            }
        }
    }

    /**
     * 获取角色列表
     *
     * @return 1.业主, 2.业主家人,3.租客, 4.租客家人,5.其他'
     */
    private List<String> getUserRoles() {
        List<String> roles = new ArrayList<>();
        roles.add("业主");
        roles.add("业主家人");
        roles.add("租客");
        roles.add("租客家人");
        roles.add("其他");
        return roles;
    }

    /**
     * 1.业主, 2.业主家人,3.租客, 4.租客家人,5.其他'
     *
     * @param roleName
     * @return
     */
    private int getRoleType(String roleName) {
        int roleType = 0;
        switch (roleName) {
            case "业主":
                roleType = 1;
                break;
            case "业主家人":
                roleType = 2;
                break;
            case "租客":
                roleType = 3;
                break;
            case "租客家人":
                roleType = 4;
                break;
            case "其他":
                roleType = 5;
                break;
            default:
                break;
        }
        return roleType;
    }
}
