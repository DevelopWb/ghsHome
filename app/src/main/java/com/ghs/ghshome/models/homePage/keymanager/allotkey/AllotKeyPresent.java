package com.ghs.ghshome.models.homePage.keymanager.allotkey;

import android.util.Log;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.AllotKeyBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/6/29 19:08
 * Description:This is AllotKeyPresent
 */
public class AllotKeyPresent extends BasePresent<AllotKeyContract.AllotKeyView<List<AllotKeyBean.DataBean>>> implements AllotKeyContract.AllotKeyPresent<AllotKeyBean.DataBean>{




    @Override
    public void getUserInfos(int userId, int roleType, int roomId, final String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }
        HttpProxy.getInstance()
                .params("userId",userId)
                .params("roleType",roleType)
                .params("roomId",roomId)
                .getToNetwork(Contract.ALLOT_KET_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().stopLoading(tag);
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content,AllotKeyBean.class),tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

    @Override
    public void modifyKeyAmount(int userRoomId, int updatedUserRoomId, int leftKeyNum) {
        HttpProxy.getInstance()
                .params("userRoomId",userRoomId)
                .params("updatedUserRoomId",updatedUserRoomId)
                .params("leftKeyNum",leftKeyNum)
                .postToNetwork(Contract.ALLOT_KET_MODIFY,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("1000",AllotKeyContract.MODIFY);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }

    @Override
    public void releaseKeyAmount(int userRoomId, int deletedUserRoomId, int deletedRoleType, int roomId) {
        HttpProxy.getInstance()
                .params("userRoomId",userRoomId)
                .params("deletedUserRoomId",deletedUserRoomId)
                .params("deletedRoleType",deletedRoleType)
                .params("roomId",roomId)
                .postToNetwork(Contract.ALLOT_KET_DELETE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(1000,AllotKeyContract.RELEASE);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }
    }


