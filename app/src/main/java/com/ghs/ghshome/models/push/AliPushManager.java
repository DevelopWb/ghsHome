package com.ghs.ghshome.models.push;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.ghs.ghshome.R;
import com.ghs.ghshome.tools.StrUtils;

public class AliPushManager {

    private static volatile AliPushManager instance = null;
    private CloudPushService pushService;
    private String TAG = "PUSHAL";
    public static String bindVilliageTag = "village_";
    public static String bindRoomIdTag = "room_";
    public static String bindUserIdTag = "user_";
    /**
     * 推送的消息类型
     */
    public static String PUSH_HOUSE_KEEPER = "housekeeper";//管家消息列表
    public static String PUSH_OFFICAL_MSG = "officialNotice";//官方公告
    public static String PUSH_VIDEO_CALL = "video_call";//官方公告
    public static String PUSH_USER_AUTH = "userAuth";//用户认证
    public static String OTHER = "other";//官方公告

    private AliPushManager() {

    }

    public static AliPushManager getInstance() {
        if (instance == null) {
            synchronized (AliPushManager.class) {
                if (instance == null) {
                    instance = new AliPushManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化推送对象
     */
    public void initPush(Context context) {
        PushServiceFactory.init(context);
        pushService = PushServiceFactory.getCloudPushService();
        pushService.setPushIntentService(MyMessageIntentService.class);
        pushRegister(context);
        initConfig(context);

    }

    /**
     *
     */
    private void initConfig(Context context) {
        if (pushService != null) {
            //设置通知栏图标
            pushService.setNotificationLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ghshome_icon));
//            //设置状态栏图标  图标不能太大  图片背景透明 要不不显示
            pushService.setNotificationSmallIcon(R.mipmap.ghshome_icon);
        }
    }

    /**
     * 注册
     */
    public void pushRegister(Context context) {
        if (pushService != null) {
            pushService.register(context, new CommonCallback() {
                @Override
                public void onSuccess(String s) {

                    Log.i(TAG, s);

                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, s);
                }
            });

        }
    }

    /**
     * 绑定小区标签
     */
    private void bindTagVillage(String tag) {

        if (pushService != null) {
            pushService.bindTag(1, new String[]{tag}, null, new CommonCallback() {
                @Override
                public void onSuccess(String s) {

                    Log.i(TAG, s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, s);
                }
            });

        }
    }

    /**
     * 解绑标签
     */

    private void unbindTagVillage(String tags) {
//        String[] a = new String[]{"village_" + tags};
//        String s = a[0];
//        Log.d(TAG, s);
        if (pushService != null) {
            pushService.unbindTag(1, new String[]{tags}, null, new CommonCallback() {//1代表本设备
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, s);
                    seachBoundTags();
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, s + "---" + s1);
                }
            });
        }


    }


    /**
     * 添加别名
     * 房间id
     *
     * @param roomTag
     */
    private void addAliasRoom(String roomTag) {
        if (pushService != null) {
            pushService.addAlias(roomTag, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, s);
                }
            });
        }

    }

    /**
     * 添加用户id
     *
     * @param useridTag
     */
    private void addAliasUser(String useridTag) {
        if (pushService != null) {

            pushService.addAlias(useridTag, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, s);
                }
            });

        }


    }

    /**
     * 删除别名
     * alias 别名（alias = null or alias.length = 0时，删除设备全部别名）
     * callback 回调
     */
    public void removeAllAlias() {
        if (pushService != null) {
            pushService.removeAlias(null, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "删除所有别名-----------" + s);
                    seachBoundAlias();
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, s);
                }
            });
        }
    }




    /**
     * @param viliageTag 小区标签
     * @param roomid     房间别名
     * @param userid     用户别名
     */
    public void bindAll(int viliageTag, int roomid, int userid) {
        unbindAll();
        //绑定小区标识
        addViliageTag(viliageTag);
        //绑定用户房间和用户别名
        addRoomAndUserAlias(roomid, userid);
    }

    /**
     * @param
     */
    public void unbindAll() {
        //解绑小区
        unbindTags();
        removeAllAlias();

    }

    /**
     * 解除所有的tag
     */
    private void unbindTags() {
        if (pushService != null) {
            pushService.listTags(1, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "绑定的tag-----待解绑------" + s);
                    if (StrUtils.isStringValueOk(s)) {
                        unbindTagVillage(s);
                    }

                }

                @Override
                public void onFailed(String s, String s1) {
                }
            });
        }

    }

    /**
     * 添加小区标签
     * 添加标签10分钟内生效 不是即时生效的
     * */

    public void addViliageTag(final int villiageTag) {
        if (pushService != null) {
            pushService.listTags(1, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "tag-----------" + s);
                    if (StrUtils.isStringValueOk(s)) {
                        if (!s.contains(bindVilliageTag + villiageTag)) {//没有绑定指定标签
                            if (s.contains(bindVilliageTag)) {
                                unbindTagVillage(s);
                            }
                            bindTagVillage(bindVilliageTag + villiageTag);
                        }
                    } else {
                        bindTagVillage(bindVilliageTag + villiageTag);
                    }
                    seachBoundTags();
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, "tag-----------" + s);
                }
            });
        }

    }

    /**
     * 添加房间和用户的别名
     */

    public void addRoomAndUserAlias(final int roomId, int userId) {
        if (pushService != null) {
            pushService.listTags(1, new CommonCallback() {
                @Override
                public void onSuccess(String s) {

                    if (StrUtils.isStringValueOk(s)) {
                        if (!s.contains(bindRoomIdTag + roomId) && s.contains(bindUserIdTag + userId)) {
                            removeAllAlias();
                        }
                        addAliasRoom(bindRoomIdTag + roomId);
                        addAliasUser(bindUserIdTag + userId);
                    } else {
                        addAliasRoom(bindRoomIdTag + roomId);
                        addAliasUser(bindUserIdTag + userId);
                    }
                    Log.i(TAG, "添加别名 -----------" + s);
                    seachBoundAlias();
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, "tag-----------" + s);
                }
            });

        }
    }
    /**
     * 查询别名
     */

    public void seachBoundAlias() {

        if (pushService != null) {
            pushService.listAliases(new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "绑定的别名-----------" + s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, "alias-error-----------" + s);
                }
            });
        }

    }


    /**
     * 查询绑定的标签
     */

    public void seachBoundTags() {
        if (pushService != null) {
            pushService.listTags(1, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "绑定的tag-----------" + s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, "tag-----------" + s);
                }
            });

        }
    }
}
