package com.ghs.ghshome.models.propertyHall.openDoorByMobile;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.BaseActivity;
import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.base.promission.CheckPermListener;
import com.ghs.ghshome.bean.BluetoothsBean;
import com.ghs.ghshome.models.homePage.seed.SeedTaskAdapter;
import com.ghs.ghshome.models.main.MainContact;
import com.ghs.ghshome.models.main.MainModel;
import com.ghs.ghshome.models.mine.useguide.UseGuideDetailActivity;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.openrecord.OpenRecordActivity;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.RemoteOpenActivity;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import io.javac.ManyBlue.bean.CharacteristicValues;
import io.javac.ManyBlue.interfaces.BaseNotifyListener;

/**
 * created by tobato
 * created date 2019/7/29 19:02.
 * application
 * 手机开门
 */
public class MobileOpenActivity extends BaseActivity implements View.OnClickListener,BaseNotifyListener.DeviceListener, BaseNotifyListener.DeviceDataListener , RequestStatus {


    /**
     * 呼叫开门
     */
    private TextView mMoblieKey;
    /**
     * 密码开门
     */
    private TextView mPasswordKey;
    /**
     * 开门记录
     */
    private TextView mKeyRecord;
    private ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    private ImageView mKeyLockExplain;
    private ImageView mKeyLockAllocation;
    private OneKeyOpenDoor oneKeyOpenDoor;
    private TextView mBlueKeyVillageName;
    private LinearLayout mOpenKeyDoor;



    @Override
    public void setLayout() {
        setContentView(R.layout.activity_lock_key);

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void getDate() {
        oneKeyOpenDoor = new OneKeyOpenDoor(this, new OneKeyOpenDoor.OnNetOpenSucceed() {
            @Override
            public void openedSucceed() {
                execSeedTask(SeedTaskAdapter.SEED_TASK_OPEN_BY_MOBILE, -1);
            }
        });
        if (UserInfoUtil.getInstance().isLoadAndSelectVillage()) {
            new MainModel().getBlueMacList(mUserInfoUtil.getVillageId(), mUserInfoUtil.getCellId(),this, MainContact.BLUE_MAC_ADDR);
        }

    }


    public void initView() {

        mMoblieKey = (TextView) findViewById(R.id.moblie_key);
        mMoblieKey.setOnClickListener(this);
        mPasswordKey = (TextView) findViewById(R.id.password_key);
        mPasswordKey.setOnClickListener(this);
        mKeyRecord = (TextView) findViewById(R.id.key_record);
        mKeyRecord.setOnClickListener(this);
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setOnClickListener(this);
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("手机开门");
        mKeyLockExplain = (ImageView) findViewById(R.id.key_lock_explain);
        mKeyLockExplain.setOnClickListener(this);

        mBlueKeyVillageName = (TextView) findViewById(R.id.blue_key_village_name);
        mBlueKeyVillageName.setText(UserInfoUtil.getInstance().getVillageName());
        mOpenKeyDoor = (LinearLayout) findViewById(R.id.open_key_door);
        mOpenKeyDoor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.moblie_key:
                //远程开门
                startActivity(new Intent(MobileOpenActivity.this, RemoteOpenActivity.class));
                break;
            case R.id.password_key:
                //密码开门
                startActivity(new Intent(MobileOpenActivity.this, PassWordOpenActivity.class));

                break;
            case R.id.key_record:
                //开门记录
                startActivity(new Intent(MobileOpenActivity.this, OpenRecordActivity.class));

                break;
            case R.id.header_left_iv:
                finish();
                break;
            case R.id.key_lock_explain:
                //说明
                startActivity(new Intent(MobileOpenActivity.this, UseGuideDetailActivity.class).putExtra(ActivityResultManager.USER_GUID_TAG,ActivityResultManager.OPEN_BY_MOBILE));
                break;

             //蓝牙开门
            case R.id.open_key_door:

                if(oneKeyOpenDoor!= null){
                    if (!oneKeyOpenDoor.isBlutoothEnable()) {
                        oneKeyOpenDoor.openBluetoothDialog();
                        return;
                    }

                    //检查定位权限
                    checkAppPermissions(new CheckPermListener() {



                        @Override
                        public void agreeAllPermission() {
                            MobileOpenTimer.clickOpendDoorNum++;
                            MobileOpenTimer.openTimeEngin(oneKeyOpenDoor,MobileOpenActivity.this);

                        }


                        @Override
                        public void selectedAllPermission() {

                        }
                    }, R.string.perm_location, PubUtil.promissions[0]);

                }
                break;
        }
    }


    @Override
    public void onDeviceScanner(BluetoothDevice device) {
        oneKeyOpenDoor.onDeviceScanner(device);

    }

    @Override
    public void onDeviceScanner(List<BluetoothDevice> device) {
        oneKeyOpenDoor.onDeviceScanner(device);

    }

    @Override
    public void onDeviceConnectState(boolean connectState, Object tag) {
        oneKeyOpenDoor.onDeviceConnectState(connectState, tag);

    }

    @Override
    public void onDeviceServiceDiscover(List<BluetoothGattService> services, Object tag) {
        oneKeyOpenDoor.onDeviceServiceDiscover(services, tag);

    }

    @Override
    public void onDeviceRegister(boolean registerState, Object tag) {
        oneKeyOpenDoor.onDeviceRegister(registerState, tag);

    }

    @Override
    public void onDeviceWriteState(boolean writeState, Object tag) {
        oneKeyOpenDoor.onDeviceWriteState(writeState, tag);

    }

    @Override
    public void onDeviceReadMessage(CharacteristicValues characteristicValues) {

        oneKeyOpenDoor.onDeviceReadMessage(characteristicValues);

    }

    @Override
    public void onDeviceNotifyMessage(CharacteristicValues characteristicValues) {

        oneKeyOpenDoor.onDeviceNotifyMessage(characteristicValues);

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        BluetoothsBean bluetoothsBean = (BluetoothsBean) o;
        if (bluetoothsBean != null) {
            Hawk.put(HawkProperty.BLUE_ADDRS, bluetoothsBean);
        }
        if (bluetoothsBean.getData() != null) {
            List<BluetoothsBean.DataBean> arrays = bluetoothsBean.getData();
            if (arrays != null && arrays.size() > 0) {
                List<String> bluetooths = new ArrayList<>();
                for (BluetoothsBean.DataBean array : arrays) {
                    bluetooths.add(array.getMacAddress());
                }
                if (bluetooths != null) {
                    Hawk.put(HawkProperty.BLUE_ADDRS_LIST, bluetooths);
                }

            }
        }

    }

    @Override
    public void onError(String tag) {

    }
}

