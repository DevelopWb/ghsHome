package com.ghs.ghshome.models.justtalk;

import android.content.Context;

import com.ghs.ghshome.models.justtalk.jcevent.JCEvent;
import com.ghs.ghshome.models.push.IsMobileType;
import com.ghs.ghshome.models.push.jcush.HwPushManager;
import com.juphoon.cloud.JCAccount;
import com.juphoon.cloud.JCAccountCallback;
import com.juphoon.cloud.JCAccountItem;
import com.juphoon.cloud.JCCall;
import com.juphoon.cloud.JCCallCallback;
import com.juphoon.cloud.JCCallItem;
import com.juphoon.cloud.JCClient;
import com.juphoon.cloud.JCClientCallback;
import com.juphoon.cloud.JCConfig;
import com.juphoon.cloud.JCMediaChannel;
import com.juphoon.cloud.JCMediaChannelCallback;
import com.juphoon.cloud.JCMediaChannelParticipant;
import com.juphoon.cloud.JCMediaChannelQueryInfo;
import com.juphoon.cloud.JCMediaDevice;
import com.juphoon.cloud.JCMediaDeviceCallback;
import com.juphoon.cloud.JCMediaDeviceVideoCanvas;
import com.juphoon.cloud.JCMessageChannel;
import com.juphoon.cloud.JCMessageChannelCallback;
import com.juphoon.cloud.JCMessageChannelItem;
import com.juphoon.cloud.JCPush;
import com.juphoon.cloud.JCStorage;
import com.juphoon.cloud.JCStorageCallback;
import com.juphoon.cloud.JCStorageItem;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import io.javac.ManyBlue.manager.EventManager;

public class JCManager implements JCClientCallback, JCMediaDeviceCallback, JCMediaChannelCallback, JCCallCallback, JCMessageChannelCallback, JCStorageCallback, JCAccountCallback {


    public static JCManager getInstance() {
        return JCManagerHolder.INSTANCE;
    }

    public Boolean pstnMode = false; // 会议的Pstn落地模式

    private Context mContext;
    public JCClient client;
    public JCCall call;
    public JCMediaDevice mediaDevice;
    public JCMediaChannel mediaChannel;
    public JCMessageChannel messageChannel;
    public JCStorage storage;
    public JCPush push;
    public JCAccount account;
    public JCConfig config;
    private static final int PUSH_TYPE_MI = 1;
    private static final int PUSH_TYPE_HMS = 2;
    private static final int PUSH_TYPE_GCM = 3;

    private int mPushType = PUSH_TYPE_MI;

    private static final String MIPUSH_APP_ID = "2882303761517848530";
    private static final String MIPUSH_APP_KEY = "5641784871530";


    public boolean initialize(Context context) {
        mContext = context.getApplicationContext();
        client = JCClient.create(mContext, "d285a9e0f778144b40334097", this, null);
        mediaDevice = JCMediaDevice.create(client, this);
       if(!mediaDevice.isSpeakerOn()){
           //开启扬声器
           mediaDevice.enableSpeaker(true);
       }
        mediaChannel = JCMediaChannel.create(client, mediaDevice, this);
        call = JCCall.create(client, mediaDevice, this);
        messageChannel = JCMessageChannel.create(client, this);
        storage = JCStorage.create(client, this);
        push = JCPush.create(client);

        account = JCAccount.create(this);

        config = JCConfig.create();

        // 本程序设置为固定方向
        mediaDevice.autoRotate = false;

        return true;
    }

    public void uninitialize() {
        if (client != null) {
            JCPush.destroy();
            JCStorage.destroy();
            JCMessageChannel.destroy();
            JCCall.destroy();
            JCMediaChannel.destroy();
            JCMediaDevice.destroy();
            JCClient.destroy();
            JCAccount.destroy();
            JCConfig.destory();
            push = null;
            storage = null;
            messageChannel = null;
            call = null;
            mediaChannel = null;
            mediaDevice = null;
            client = null;
            account = null;
            config = null;
        }
    }

    //   登录的监听
    @Override
    public void onLogin(boolean b, int i) {

        if (IsMobileType.isHw()) {
            mPushType = 1;
        } else {
            mPushType = 1;
        }

        if (b) {
            switch (mPushType) {
                case PUSH_TYPE_MI:
                    MiPushClient.registerPush(mContext, MIPUSH_APP_ID, MIPUSH_APP_KEY);
                    // 小米辅助推送通道
//                    MiPushRegister.register(mContext, MIPUSH_APP_ID, MIPUSH_APP_KEY);

                    break;
                case PUSH_TYPE_HMS:
//                    HMSPush.start(mContext);
                    //华为辅助推送通道
//                    HuaWeiRegister.register(mContext);
                    break;
            }
        }

    }

    @Override
    public void onLogout(int i) {

        //取消注册
        boolean hw = IsMobileType.isHw();
        if (hw) {
            HwPushManager.getInstance().stop();

        } else {

            MiPushClient.unregisterPush(mContext);

        }



    }

    @Override
    public void onClientStateChange(int i, int i1) {

    }

    //    摄像头改变的监听
    @Override
    public void onCameraUpdate() {

    }

    @Override
    public void onAudioOutputTypeChange(boolean b) {

    }

    @Override
    public void onRenderReceived(JCMediaDeviceVideoCanvas jcMediaDeviceVideoCanvas) {

    }

    @Override
    public void onRenderStart(JCMediaDeviceVideoCanvas jcMediaDeviceVideoCanvas) {

    }


    @Override
    public void onMediaChannelStateChange(int i, int i1) {

    }

    @Override
    public void onMediaChannelPropertyChange(JCMediaChannel.PropChangeParam propChangeParam) {

    }


    @Override
    public void onJoin(boolean b, int i, String s) {

    }

    @Override
    public void onLeave(int i, String s) {

    }

    @Override
    public void onStop(boolean b, int i) {

    }

    @Override
    public void onQuery(int i, boolean b, int i1, JCMediaChannelQueryInfo jcMediaChannelQueryInfo) {

    }

    @Override
    public void onParticipantJoin(JCMediaChannelParticipant jcMediaChannelParticipant) {

    }

    @Override
    public void onParticipantLeft(JCMediaChannelParticipant jcMediaChannelParticipant) {

    }

    @Override
    public void onParticipantUpdate(JCMediaChannelParticipant jcMediaChannelParticipant, JCMediaChannelParticipant.ChangeParam changeParam) {

    }


    @Override
    public void onMessageReceive(String s, String s1, String s2) {

    }

    @Override
    public void onInviteSipUserResult(int i, boolean b, int i1) {

    }

    //    来电话的监听
    @Override
    public void onCallItemAdd(JCCallItem jcCallItem) {

        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_ADD));
        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_UI));

    }

    @Override
    public void onCallItemRemove(JCCallItem jcCallItem, int i, String s) {
        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_REMOVE));
        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_UI));
    }

    @Override
    public void onCallItemUpdate(JCCallItem jcCallItem, JCCallItem.ChangeParam changeParam) {
        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_UPDATE));
        EventManager.getLibraryEvent().post(new JCEvent(JCEvent.EventType.CALL_UI));
    }


    @Override
    public void onMessageReceive(String s, String s1, JCCallItem jcCallItem) {

    }

    //消息通道
    @Override
    public void onMessageSendUpdate(JCMessageChannelItem jcMessageChannelItem) {

    }

    @Override
    public void onMessageRecv(JCMessageChannelItem jcMessageChannelItem) {

    }

    @Override
    public void onFileUpdate(JCStorageItem jcStorageItem) {

    }

    @Override
    public void onFileResult(JCStorageItem jcStorageItem) {

    }

    @Override
    public void onQueryUserStatusResult(int i, boolean b, List<JCAccountItem> list) {

    }


    private static final class JCManagerHolder {
        private static final JCManager INSTANCE = new JCManager();
    }
}
