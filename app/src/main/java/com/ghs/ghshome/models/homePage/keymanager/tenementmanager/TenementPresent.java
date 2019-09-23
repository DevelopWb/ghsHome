package com.ghs.ghshome.models.homePage.keymanager.tenementmanager;

import android.util.Log;

import com.ghs.ghshome.base.BasePresent;
import com.ghs.ghshome.base.network.networkProxy.HttpProxy;
import com.ghs.ghshome.base.network.okgo.NetResponseCallBack;
import com.ghs.ghshome.bean.MobileBean;
import com.ghs.ghshome.bean.TenementRoomBean;
import com.ghs.ghshome.bean.VillageNoticeBean;
import com.ghs.ghshome.tools.Contract;
import com.ghs.ghshome.tools.GsonManager;

/**
 * created by guohainan
 * created date 2019/9/18
 * application
 * package name com.ghs.ghshome.models.homePage.keymanager.tenementmanager
 */
public class TenementPresent extends BasePresent<TenementContract.TenementView>implements TenementContract.ITenementPresent {
    @Override
    public void getUserRoomList(int roomId, String tag) {
        HttpProxy.getInstance()
                .params("roomId", roomId)
                .getToNetwork(Contract.GET_USERROMM_LIST,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {

                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, TenementRoomBean.class), tag);
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
    public void addTenement(int userId, int roomId, int roleType, String mobile, String fullName, String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("roomId", roomId)
                .params("roleType", roleType)
                .params("mobile", mobile)
                .params("fullName", fullName)
                .postToNetwork(Contract.ADD_KEY,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {

                        Log.i("TAG",content);

                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, TenementRoomBean.class), tag);
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
    public void removeTenment(int userRoomId, int deletedUserRoomId, String tag) {

        HttpProxy.getInstance()
                .params("userRoomId", userRoomId)
                .params("deletedUserRoomId", deletedUserRoomId)
                .postToNetwork(Contract.DELETE_KEY,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, MobileBean.class), tag);
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
    public void getFullNameByMobile(String mobile, String tag) {


        HttpProxy.getInstance()
                .params("mobile", mobile)
                .postToNetwork(Contract.GET_USERNAME_MOBILE,new NetResponseCallBack() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, MobileBean.class), tag);
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
