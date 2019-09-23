package com.ghs.ghshome.models.propertyHall.openDoorByMobile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ghs.ghshome.R;
import com.ghs.ghshome.base.network.RequestStatus;
import com.ghs.ghshome.bean.BluetoothsBean;
import com.ghs.ghshome.custom.LoadingDialog;
import com.ghs.ghshome.models.main.MainContact;
import com.ghs.ghshome.models.main.MainModel;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.RemoteOpenContract;
import com.ghs.ghshome.models.propertyHall.openDoorByMobile.remoteopen.RemoteOpenModel;
import com.ghs.ghshome.tools.ActivityResultManager;
import com.ghs.ghshome.tools.HawkProperty;
import com.ghs.ghshome.base.network.NetWorkUtil;
import com.ghs.ghshome.tools.PubUtil;
import com.ghs.ghshome.tools.StrUtils;
import com.ghs.ghshome.tools.ToastUtil;
import com.ghs.ghshome.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.javac.ManyBlue.ManyBlue;
import io.javac.ManyBlue.bean.CharacteristicValues;
import io.javac.ManyBlue.bean.UUIDMessage;
import io.javac.ManyBlue.interfaces.BaseNotifyListener;

/**
 * Author:wang_sir
 * Time:2019/1/18 10:46
 * Description:This is OneKeyOpenDoor   蓝牙开门的逻辑
 */
public class OneKeyOpenDoor implements BaseNotifyListener.DeviceListener, BaseNotifyListener.DeviceDataListener, RequestStatus {
    private Context mContext;
    private BluetoothAdapter myBluetoothAdapter;
    private CountDownTimer timer; //倒计时
    private int countDownTime = 6;
    private int time;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    private String TAG = "oneKeyOpenDoor";
    private boolean openFinished = false;//开门过程是否结束
    private String serverName = "";//匹配的blueServer

    private OnNetOpenSucceed openSucceedCallBack;

    public OneKeyOpenDoor(Context mContext, OnNetOpenSucceed openSucceed) {
        this.mContext = mContext;
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.openSucceedCallBack = openSucceed;
    }

    /**
     * 检查蓝牙是否开启
     */
    public boolean isBlutoothEnable() {
        return myBluetoothAdapter.isEnabled();
    }

    /**
     * 开门
     */
    public void oneKeyOpenTheDoor() {

        if (!myBluetoothAdapter.isEnabled()) {
            openBluetoothDialog();


        } else {
            startScanBluetoothDevice();
        }
    }

    /**
     * 提示打开蓝牙
     */
    public void openBluetoothDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setMessage("开门功能需要打开蓝牙，是否打开？")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mContext.startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                    }
                })
                .setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);
    }

    /**
     * 设置alertdialog的宽高
     *
     * @param dialog
     * @param width  -1代表屏幕宽度  0 代表 wrap_content  其他就是自定义值了
     * @param height
     */
    public void setAlertDialogHeightWidth(AlertDialog dialog, int width, int height) {
        // 设置dialog的宽度
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (-1 == width) {
            params.width = PubUtil.ScreenWidth;
        } else if (0 == width) {
            params.width = params.width;
        } else {
            params.width = width;
        }
        if (-1 == height) {
            params.height = PubUtil.ScreenHeight;
        } else if (0 == height) {
            params.height = params.height;
        } else {
            params.height = height;
        }
        dialog.getWindow().setAttributes(params);
    }

    /**
     * 开始扫描蓝牙设备
     */
    private void startScanBluetoothDevice() {
        if (timer != null) {
            timer.cancel();
            timer = null;

        }
        time = countDownTime;
        openFinished = false;//开门开始，开门过程未结束
        ManyBlue.blueStartScaner();
        Log.d(TAG, "开始扫描" + "-------------" + sdf.format(new Date()));
        LoadingDialog.getInstance().showProgress(mContext);
        /** 倒计时6秒，一次1秒 */
        // 未扫描到蓝牙server，弹出密码框
        timer = new CountDownTimer(countDownTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                time--;
                Log.d(TAG, "扫描倒计时" + time + "-------------" + sdf.format(new Date()));
                if (openFinished) {
                    timer.cancel();
                }
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "开门过程" + openFinished + "-------------" + sdf.format(new Date()));

                if (!openFinished) {// 开门过程未结束，弹出密码提示框
                    getPwdToShow();
                }

            }
        }.start();
    }

    /**
     * 展现本地密钥或者网络密钥
     */
    private void getPwdToShow() {

        if (NetWorkUtil.isNetworkAvailable()) {
            Log.d(TAG, "有网络，请求网络上的密钥" + "-------------" + sdf.format(new Date()));
            new RemoteOpenModel().getPassword(UserInfoUtil.getInstance().getRoomUserId(), this, RemoteOpenContract.GET_PWD);

        } else {
            Log.d(TAG, "无网络，请求网络上的密钥" + "-------------" + sdf.format(new Date()));

            showPwdDialog(false, "");

        }
    }

    /**
     * 弹出密码框
     * isNet : true 网络请求返回的密码，false 本地密钥
     */
    private void showPwdDialog(boolean isNet, String pwd) {
        LoadingDialog.getInstance().dismissProgress();
        ManyBlue.blueStopScaner();
        openFinished = true;
        View view = LayoutInflater.from(mContext).inflate(R.layout.open_door_pwd_layout, null);
        TextView textView = view.findViewById(R.id.open_door_pwd_content_tv);
        if (isNet) {
            textView.setText(pwd);
        } else {
            textView.setText(UserInfoUtil.getInstance().getLockPassword());
        }
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .create();
        dialog.show();

        //设置弹出框的长宽
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(PubUtil.ScreenWidth / 6 * 5, PubUtil.ScreenHeight / 2);

        }
    }

    @Override
    public void onDeviceScanner(BluetoothDevice device) {
        String name = device.getName();
        if (name != null) {
            Log.d(TAG, "蓝牙门禁设备名称" + name + "-------------" + sdf.format(new Date()));
            if (searchedBlueDevice(name)) {//搜索到蓝牙门禁设备
                Log.d(TAG, "搜索到蓝牙门禁设备" + name + "-------------" + sdf.format(new Date()));

                ManyBlue.blueStopScaner();
//                if (!bluetooths_local.contains(name)) {
//                    bluetooths_local.add(name);
//
//                    Log.d(TAG, "搜索到服务端" + "-----------" + name);
//                }
                serverName = name;
                //判断是否有网络，如果有网络，直接走网络开门，如果没有网络，走蓝牙开门
                if (NetWorkUtil.isNetworkAvailable()) {
                    Log.d(TAG, "有网络，直接走网络开锁" + sdf.format(new Date()));
                    new MainModel().openDoorByNet(getDeviceId(serverName), UserInfoUtil.getInstance().getRoomUserId(), this);


                } else {
                    Log.d(TAG, "无网络， 开始连接蓝牙服务端" + sdf.format(new Date()));
                    ManyBlue.blueConnectDevice(name, name);

                }


            }
        }
    }

    /**
     * 匹配到设备
     *
     * @param deviceName
     * @return
     */
    private boolean searchedBlueDevice(String deviceName) {
        List<String> arrays = Hawk.get(HawkProperty.BLUE_ADDRS_LIST, new ArrayList<String>());
        Log.d(TAG, "蓝牙地址列表" + arrays.toString() + "-------------" + sdf.format(new Date()));

        return arrays.contains(deviceName);

    }

    /**
     * 获取设备id
     *
     * @param deviceName
     * @return
     */
    private int getDeviceId(String deviceName) {
        int deviceId = 0;
        BluetoothsBean bluetoothsBean = Hawk.get(HawkProperty.BLUE_ADDRS);
        List<BluetoothsBean.DataBean> beans = bluetoothsBean.getData();
        if (beans != null && beans.size() > 0) {
            for (BluetoothsBean.DataBean bean : beans) {
                if (bean.getMacAddress().equals(deviceName)) {
                    deviceId = bean.getId();
                    break;
                }
            }
        }
        return deviceId;
    }

    @Override
    public void onDeviceScanner(List<BluetoothDevice> device) {

    }

    @Override
    public void onDeviceConnectState(boolean connectState, Object tag) {
        if (connectState) {
            Log.d(TAG, "已连接" + "-------------" + sdf.format(new Date()));

            /** 倒计时60秒，一次1秒 */
            CountDownTimer timer = new CountDownTimer(1000, 500) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    ManyBlue.blueWriteDataByteArray(ActivityResultManager.virtureKey.getBytes(), serverName);
                    Log.d(TAG, "已输入key" + "-------------" + sdf.format(new Date()));

                }
            }.start();

        } else {
            getPwdToShow();
            Log.d(TAG, "连接失败,弹出密码框" + "-------------" + sdf.format(new Date()));
// 弹出密钥窗口

        }


    }

    @Override
    public void onDeviceServiceDiscover(List<BluetoothGattService> services, Object tag) {
        String serverUuid = null;
        String readUuid = null;
        for (BluetoothGattService service : services) {
            Log.e(TAG, service.getUuid().toString() + "  ----onDeviceServiceDiscover------");
            if (!service.getUuid().toString().startsWith("00001801") && !service.getUuid().toString().startsWith("00001800")) {
                serverUuid = service.getUuid().toString();
                List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
                if (characteristics.size() != 0) {
                    readUuid = characteristics.get(0).getUuid().toString();
                }
                for (BluetoothGattCharacteristic characteristic : characteristics) {
                    Log.e("characteristic", characteristic.getUuid().toString());
                }
            }
        }
        //创建UUID的配置类
        final UUIDMessage uuidMessage = new UUIDMessage();
        //需要注册的服务UUID
        uuidMessage.setCharac_uuid_service(serverUuid);

        //添加通道UUID
        uuidMessage.setCharac_uuid_write(readUuid);
        uuidMessage.setCharac_uuid_read(readUuid);
        uuidMessage.setCharac_uuid_notify(readUuid);

        ManyBlue.blueRegisterDevice(uuidMessage, tag);

    }

    @Override
    public void onDeviceRegister(boolean registerState, Object tag) {

    }

    @Override
    public void onDeviceWriteState(boolean writeState, Object tag) {

    }

    @Override
    public void onDeviceReadMessage(CharacteristicValues characteristicValues) {

    }

    /**
     * 收到服务端返回的数据
     *
     * @param characteristicValues
     */
    @Override
    public void onDeviceNotifyMessage(CharacteristicValues characteristicValues) {
        String result = characteristicValues.getStrValue();
        Log.d(TAG, "收到服务的的回执" + result + "-------------" + sdf.format(new Date()));
        if ("1000".equals(result)) {
            openFinished = true;
            LoadingDialog.getInstance().dismissProgress();
            ToastUtil.showNormalToast(mContext, Toast.LENGTH_LONG, "开锁成功");
        } else {
            // 弹出密码框
            getPwdToShow();
        }
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        switch (tag) {
            case MainContact.OPEN_BY_NET:

                if ("1000".equals(o)) {//网络开门成功
                    LoadingDialog.getInstance().dismissProgress();
                    openFinished = true;
                    Log.d(TAG, "网络开门成功" + sdf.format(new Date()));
                    ToastUtil.showNormalToast(mContext, Toast.LENGTH_SHORT, "开门成功");
                    if (openSucceedCallBack != null) {
                        openSucceedCallBack.openedSucceed();
                    }

                } else {// 网络开门失败，蓝牙连接服务端

                    openDoorByNetFailed();

                }
                break;
            case RemoteOpenContract.GET_PWD:
                Log.d(TAG, "获取网络密钥成功" + sdf.format(new Date()));

                String pwd = (String) o;
                showPwdDialog(true, pwd);
                break;
            default:
                break;
        }
    }

    /**
     * 网络开门失败
     */
    private void openDoorByNetFailed() {
        Log.d(TAG, "网络开门失败" + sdf.format(new Date()));
        if (StrUtils.isStringValueOk(serverName)) {//已知蓝牙门禁设备
            Log.d(TAG, "已搜索到蓝牙设备，网络开门失败，开始连接蓝牙服务端");

            ManyBlue.blueConnectDevice(serverName, serverName);
        } else {

            if (NetWorkUtil.isNetworkAvailable()) {
                Log.d(TAG, "未搜索到蓝牙设备，网络开门失败，请求网络密钥");
                new RemoteOpenModel().getPassword(UserInfoUtil.getInstance().getRoomUserId(), this, RemoteOpenContract.GET_PWD);

            } else {
                Log.d(TAG, "未搜索到蓝牙设备，网络开门失败，无网络，展示本地密钥");

                showPwdDialog(false, "");

            }

        }
    }

    @Override
    public void onError(String tag) {
        switch (tag) {
            case MainContact.OPEN_BY_NET:
                openDoorByNetFailed();
                break;
            case RemoteOpenContract.GET_PWD:
                showPwdDialog(false, "");
                break;
            default:
                break;
        }
    }

    interface OnNetOpenSucceed {
        void openedSucceed();
    }

}
